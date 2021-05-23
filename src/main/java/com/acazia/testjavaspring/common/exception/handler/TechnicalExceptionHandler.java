package com.acazia.testjavaspring.common.exception.handler;

import com.acazia.testjavaspring.common.exception.AbstractException;
import com.acazia.testjavaspring.common.exception.TechnicalException;
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
public class TechnicalExceptionHandler extends AbstractExceptionHandler {

    private final MessageSource technicalMessages;

    @Autowired
    public TechnicalExceptionHandler(MessageSource technicalMessages) {
        this.technicalMessages = technicalMessages;
    }

    @Override
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handle(AbstractException ex, Locale locale) {
        return super.handle(ex, locale);
    }

    @Override
    protected MessageSource getMessageSource() {
        return technicalMessages;
    }
}