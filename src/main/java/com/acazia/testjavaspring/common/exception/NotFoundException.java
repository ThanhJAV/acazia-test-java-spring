package com.acazia.testjavaspring.common.exception;


import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends AbstractException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(AlertMessages alert, Throwable cause) {
        super(alert, cause);
    }

    public NotFoundException(AlertMessages alert) {
        super(alert);
    }

    public NotFoundException(List<AlertMessages> alerts, Throwable cause) {
        super(alerts, cause);
    }

    public NotFoundException(List<AlertMessages> alerts) {
        super(alerts);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
