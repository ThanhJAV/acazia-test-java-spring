package com.acazia.testjavaspring.common.exception;

import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final transient List<AlertMessages> alerts;

    public ClientException(Throwable cause) {
        super(cause);
        alerts = Collections.emptyList();
    }

    public ClientException(List<AlertMessages> alerts) {
        this.alerts = alerts;
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
        alerts = Collections.emptyList();
    }

    public ClientException(String message) {
        super(message);
        alerts = Collections.emptyList();
    }

    public ClientException(AlertMessages alert) {
        this.alerts = new ArrayList<>();
        this.alerts.add(alert);
    }

    public ClientException(AlertMessages error, Throwable cause) {
        super(cause);
        this.alerts = new ArrayList<>();
        this.alerts.add(error);
    }

    public ClientException(List<AlertMessages> errors, Throwable cause) {
        super(cause);
        this.alerts = errors;
    }

    /**
     * @return the error code that are provided by the application
     */
    public List<AlertMessages> getAlertCodes() {
        return alerts;
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
