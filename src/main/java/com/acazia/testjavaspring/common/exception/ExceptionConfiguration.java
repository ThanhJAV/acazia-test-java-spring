package com.acazia.testjavaspring.common.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ExceptionConfiguration {
    private static final String MESSAGE_BUSINESS_PATH    =
                                                      "${business.message.path:classpath:/exception/businessExceptionLabels}";
    private static final String MESSAGE_APPLICATION_PATH =
                                                         "${application.message.path:classpath:/exception/applicationExceptionLabels}";
    private static final String MESSAGE_TECHNICAL_PATH   =
                                                       "${technical.message.path:classpath:/exception/technicalExceptionLabels}";
    private static final String MESSAGE_VALIDATION_PATH  =
                                                        "${validation.message.path:classpath:/exception/validationExceptionLabels}";
    private static final String MESSAGE_AUTHENTICATION_PATH  =
    		"${authentication.message.path:classpath:/exception/authenticationExceptionLabels}";
    
    
    @Value(value = MESSAGE_BUSINESS_PATH)
    private String              businessMessagePath;

    @Value(value = MESSAGE_APPLICATION_PATH)
    private String              applicationMessagePath;

    @Value(value = MESSAGE_TECHNICAL_PATH)
    private String              technicalMessagePath;

    @Value(value = MESSAGE_VALIDATION_PATH)
    private String              validationMessagePath;
    @Value(value = MESSAGE_AUTHENTICATION_PATH)
    private String              authenticationMessagePath;

    @Bean("businessMessages")
    public MessageSource businessMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(businessMessagePath);

        return messageSource;
    }

    @Bean("applicationMessages")
    public MessageSource applicationMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(applicationMessagePath);

        return messageSource;
    }

    @Bean("technicalMessages")
    public MessageSource technicalMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(technicalMessagePath);

        return messageSource;
    }

    @Bean("validationMessages")
    public MessageSource validationMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(validationMessagePath);

        return messageSource;
    }
    
    @Bean("authenticationMessages")
    public MessageSource authenticationMessages() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(authenticationMessagePath);

        return messageSource;
    }
}
