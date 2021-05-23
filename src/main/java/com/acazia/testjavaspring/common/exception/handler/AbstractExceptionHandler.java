package com.acazia.testjavaspring.common.exception.handler;


import com.acazia.testjavaspring.common.exception.AbstractException;
import com.acazia.testjavaspring.common.exception.constant.AlertType;
import com.acazia.testjavaspring.common.exception.pojo.Alert;
import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import com.acazia.testjavaspring.common.exception.pojo.AlertWrapper;
import com.acazia.testjavaspring.common.exception.pojo.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExceptionHandler.class);

    /**
     * Handle the Exception in RestController during implementation
     *
     * @param exception
     * @param locale
     * @return
     * @throws Throwable
     */
    public ResponseEntity<Object> handle(AbstractException exception, Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info(exception.getMessage(), exception);
        }

        AlertWrapper alertWrapper = new AlertWrapper();
        List<Alert> alertCodes = new ArrayList<>();
        if (CollectionUtils.isEmpty(exception.getAlertCodes())) {
            alertCodes.add(generateUnhandledError(exception));
        } else {
            try {
                alertCodes.addAll(exception.getAlertCodes().stream().map(ex -> {
                    Alert alert = new Alert();
                    alert.setCode(ex.getAlertCode().getCode());
                    alert.setMessage(getMessageSource().getMessage(ex.getAlertCode().getLabel(), ex.getArgs(), locale));
                    alert.setType(ex.getAlertCode().getType());
                    return alert;
                }).collect(Collectors.toList()));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                alertCodes.add(generateUnhandledError(e));
            }

        }
        HttpStatus httpStatus = exception.getStatus();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Set<Alert> setAlert = new HashSet<>();
        setAlert.addAll(alertCodes);
        alertWrapper.setAlerts(setAlert);
        return new ResponseEntity<>(alertWrapper, headers, httpStatus);
    }

    protected static Alert generateUnhandledError(Exception ex) {
        Alert alert = new Alert();
        alert.setCode("TECHNICAL");
        alert.setMessage(ex.getMessage());
        alert.setType(AlertType.ERROR);
        return alert;
    }

    /**
     * The {@link ResourceBundle} object to get the error message from error code that defined in
     * the message bundle files.
     *
     * @return
     */
    protected abstract MessageSource getMessageSource();

    public static ErrorMessage generateErrorMessage(MessageSource messageSource, AlertMessages error, Locale locale) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(error.getAlertCode().getCode());
        errorMessage.setDetail(messageSource.getMessage(error.getAlertCode().getLabel(), error.getArgs(), locale));
        return errorMessage;
    }
}
