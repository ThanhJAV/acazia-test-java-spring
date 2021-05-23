package com.acazia.testjavaspring.common.exception.handler;


import com.acazia.testjavaspring.common.exception.AbstractException;
import com.acazia.testjavaspring.common.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApplicationExceptionHandler extends AbstractExceptionHandler {

    private final MessageSource applicationMessages;

    @Autowired
    public ApplicationExceptionHandler(MessageSource applicationMessages) {
        this.applicationMessages = applicationMessages;
    }

    @Override
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handle(AbstractException ex, Locale locale) {
        return super.handle(ex, locale);
    }

    @Override
    protected MessageSource getMessageSource() {
        return applicationMessages;
    }
}
