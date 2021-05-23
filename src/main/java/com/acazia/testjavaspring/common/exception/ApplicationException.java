package com.acazia.testjavaspring.common.exception;


import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApplicationException extends AbstractException {
    private static final long serialVersionUID = -6291910029343605439L;

    public ApplicationException(AlertMessages alert, Throwable cause) {
        super(alert, cause);
    }

    public ApplicationException(AlertMessages alert) {
        super(alert);
    }

    public ApplicationException(List<AlertMessages> alerts, Throwable cause) {
        super(alerts, cause);
    }

    public ApplicationException(List<AlertMessages> alerts) {
        super(alerts);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
