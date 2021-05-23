package com.acazia.testjavaspring.common.exception.handler;


import com.acazia.testjavaspring.common.exception.ClientException;
import com.acazia.testjavaspring.common.exception.pojo.Alert;
import com.acazia.testjavaspring.common.exception.pojo.AlertWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ClientExceptionHandler extends AbstractExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientExceptionHandler.class);

    /**
     * Handle the Exception in Rest Client during implementation
     *
     * @param exception
     * @param locale
     * @return
     * @throws Throwable
     */
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handle(ClientException exception, Locale locale) {
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
        HttpHeaders headers = new HttpHeaders();
        HttpStatus httpStatus = exception.getStatus();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Set<Alert> setAlert = new HashSet<>();
        setAlert.addAll(alertCodes);
        alertWrapper.setAlerts(setAlert);
        return new ResponseEntity<>(alertWrapper, headers, httpStatus);
    }

    protected MessageSource getMessageSource() {
        return null;
    }
}
