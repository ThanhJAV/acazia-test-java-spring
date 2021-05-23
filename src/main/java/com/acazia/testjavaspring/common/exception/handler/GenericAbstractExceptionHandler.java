package com.acazia.testjavaspring.common.exception.handler;


import com.acazia.testjavaspring.common.exception.AbstractException;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GenericAbstractExceptionHandler extends AbstractExceptionHandler {

    @Override
    @ExceptionHandler(value = {AbstractException.class})
    public ResponseEntity<Object> handle(AbstractException exception, Locale locale) {
        return super.handle(exception, locale);
    }

    @Override
    protected MessageSource getMessageSource() {
        return null;
    }


}
