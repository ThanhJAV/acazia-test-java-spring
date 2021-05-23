package com.acazia.testjavaspring.common.exception;

import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BusinessException extends AbstractException {

    private static final long serialVersionUID = 1254672957987028725L;

    public BusinessException(AlertMessages alert, Throwable cause) {
        super(alert, cause);
    }

    public BusinessException(AlertMessages alert) {
        super(alert);
    }

    public BusinessException(List<AlertMessages> alerts, Throwable cause) {
        super(alerts, cause);
    }

    public BusinessException(List<AlertMessages> alerts) {
        super(alerts);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
