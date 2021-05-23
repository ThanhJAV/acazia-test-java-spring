package com.acazia.testjavaspring.common.exception;


import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import org.springframework.http.HttpStatus;

import java.util.List;

public class AuthenticationException extends AbstractException {

    private static final long serialVersionUID = 2211027557200903141L;

    public AuthenticationException(AlertMessages alert, Throwable cause) {
        super(alert, cause);
    }

    public AuthenticationException(AlertMessages alert) {
        super(alert);
    }

    public AuthenticationException(List<AlertMessages> alerts, Throwable cause) {
        super(alerts, cause);
    }

    public AuthenticationException(List<AlertMessages> alerts) {
        super(alerts);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}