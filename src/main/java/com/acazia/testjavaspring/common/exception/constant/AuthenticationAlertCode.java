package com.acazia.testjavaspring.common.exception.constant;


import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

import java.util.Optional;

public enum AuthenticationAlertCode implements IAlertCode {
    USER_OR_PASSWORD_IS_INCORRECT("0000001", "user.or.password.is.incorrect", AlertType.ERROR),
    TOKEN_INVALID("0000002", "token.invalid", AlertType.ERROR),
    TOKEN_EXPIRED("0000003", "token.expired", AlertType.ERROR),
    ACCESS_DENIED("0000004", "access.denied", AlertType.ERROR),
    UNAUTHORIZED("0000005", "unauthorized", AlertType.ERROR);


    private AlertCode alertCode;

    AuthenticationAlertCode(final String code, final String label, final AlertType alertType) {
        alertCode = new AlertCode(code, label, alertType);
    }

    /**
     * get ErrorCode by label
     *
     * @param label
     * @return ErrorCode
     */
    public static Optional<AuthenticationAlertCode> findByLabel(String label) {
        for (AuthenticationAlertCode err : AuthenticationAlertCode.values()) {
            if (err.getLabel().equals(label))
                return Optional.of(err);
        }
        return Optional.empty();
    }

    @Override
    public String getCode() {
        return alertCode.getCode();
    }

    @Override
    public String getLabel() {
        return alertCode.getLabel();
    }

    @Override
    public AlertType getType() {
        return alertCode.getType();
    }
}
