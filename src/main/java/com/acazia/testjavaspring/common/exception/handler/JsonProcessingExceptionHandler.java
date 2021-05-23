package com.acazia.testjavaspring.common.exception.handler;

import com.acazia.testjavaspring.common.exception.constant.AlertType;
import com.acazia.testjavaspring.common.exception.pojo.Alert;
import com.acazia.testjavaspring.common.exception.pojo.AlertWrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class JsonProcessingExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProcessingExceptionHandler.class);

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;
    private static final String COMMON_ERROR_CODE = "malformed.json";

    /**
     * Error message contains square bracket with full type path inside
     */
    private static final String SQUARE_BRACKET_REGEX = "(.*)\\[(.*?)\\](.*)";
    /**
     * START_OBJECT or START_ARRAY will be showed in the error message when
     * unexpected object/array type was passed to different field type. <br/>
     * E.g: pass an object to a number field.
     */
    private static final String START_OBJECT_OR_ARRAY = "START_";
    /**
     * "VALUE_" string be showed in the error message if unexpected SCALAR type
     * passed to different field type. <br/>
     * E.g: pass String object to an Array type field.
     */
    private static final String START_SCALAR_VALUE = "VALUE_";
    /**
     * Duplicate field 'mobilePhone'\n at [Source:
     * org.glassfish.jersey.message.internal.
     * ReaderInterceptorExecutor$UnCloseableInputStream
     *
     * @939e879; line: 15, column: 23]
     */
    private static final String DUPLICATE_FIELD = "Duplicate field";

    @ExceptionHandler(value = {JsonMappingException.class})
    public ResponseEntity<AlertWrapper> handle(JsonProcessingException e, WebRequest request, Locale locale) {
        JsonMappingException exception = (JsonMappingException) e;
        LOGGER.error("JsonMappingException: Malformed Json!", exception);
        String message = exception.getOriginalMessage();

        if (message != null) {
            if (message.matches(SQUARE_BRACKET_REGEX)) {
                message = "Json format or structure is invalid.";
            } else if (message.contains(START_OBJECT_OR_ARRAY) || message.contains(START_SCALAR_VALUE)) {
                message = "Json contains object that mismatch type.";
            } else if (message.contains(DUPLICATE_FIELD)) {
                message = removeDuplicateField(message);
            }
        }
        return buildResponse(message);
    }

    @ExceptionHandler(value = {UnrecognizedPropertyException.class})
    public ResponseEntity<AlertWrapper> handleUnrecognizedPropertyException(JsonProcessingException e, Locale locale) {
        UnrecognizedPropertyException exception = (UnrecognizedPropertyException) e;
        LOGGER.error("UnrecognizedPropertyException: Malformed Json!", exception);
        String message = exception.getPropertyName() + " field is unrecognized";
        return buildResponse(message);
    }

    @ExceptionHandler(value = {JsonParseException.class})
    public ResponseEntity<AlertWrapper> handleJsonParseException(JsonProcessingException e, Locale locale) {
        JsonParseException exception = (JsonParseException) e;
        LOGGER.error("JsonParseException: Malformed Json!", exception);
        return buildResponse(exception.getOriginalMessage());
    }

    /**
     * message of system: Duplicate field 'mobilePhone'\n at [Source:
     * org.glassfish.jersey.message.internal.
     * ReaderInterceptorExecutor$UnCloseableInputStream@939e879; line: 15,
     * column: 23] -> remove from \n to the end of message
     *
     * @param message
     * @return String
     */
    private static String removeDuplicateField(String message) {
        int isN = message.indexOf('\n');
        if (isN > 0) {
            return (String) message.subSequence(0, isN);
        }
        return message;
    }

    private static ResponseEntity<AlertWrapper> buildResponse(String message) {
        Alert alert = new Alert();
        alert.setCode(COMMON_ERROR_CODE);
        alert.setMessage(message);
        alert.setType(AlertType.ERROR);
        AlertWrapper alertWrapper = new AlertWrapper();
        List<Alert> alertCodes = new ArrayList<>();
        alertCodes.add(alert);
        Set<Alert> setAlert = new HashSet<>();
        setAlert.addAll(alertCodes);
        alertWrapper.setAlerts(setAlert);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(alertWrapper, headers, DEFAULT_STATUS);
    }

}
