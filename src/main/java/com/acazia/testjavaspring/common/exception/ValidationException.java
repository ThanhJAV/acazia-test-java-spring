package com.acazia.testjavaspring.common.exception;

import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.List;


public class ValidationException extends AbstractException {

    private static final long serialVersionUID = 290437354054940749L;

    public ValidationException(AlertMessages alert, Throwable cause) {
        super(alert, cause);
    }

    public ValidationException(AlertMessages alert) {
        super(alert);
    }

    public ValidationException(List<AlertMessages> alerts, Throwable cause) {
        super(alerts, cause);
    }

    public ValidationException(List<AlertMessages> alerts) {
        super(alerts);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
