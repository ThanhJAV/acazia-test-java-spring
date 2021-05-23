package com.acazia.testjavaspring.common.exception;

import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final transient List<AlertMessages> alerts;

    public AbstractException(Throwable cause) {
        super(cause);
        this.alerts = Collections.emptyList();
    }

    public AbstractException(List<AlertMessages> alerts) {
        this.alerts = alerts;
    }

    public AbstractException(AlertMessages alert) {
        this.alerts = new ArrayList<>();
        this.alerts.add(alert);
    }

    public AbstractException(AlertMessages error, Throwable cause) {
        super(cause);
        this.alerts = new ArrayList<>();
        this.alerts.add(error);
    }

    public AbstractException(List<AlertMessages> errors, Throwable cause) {
        super(cause);
        this.alerts = errors;
    }

    /**
     * @return the error code that are provided by the application
     */
    public List<AlertMessages> getAlertCodes() {
        return alerts;
    }

    public abstract HttpStatus getStatus();
}
