package com.acazia.testjavaspring.common.exception.constant;


import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

import java.util.Optional;

public enum TechnicalAlertCode implements IAlertCode {

    GENERIC_TECHNICAL_EXCEPTION("0000001", "generic.technical.exception", AlertType.ERROR),
    UNABLE_GET_INFOMATION("0000002", "unable.get.infomation", AlertType.ERROR),
    SOMETHING_WENT_WRONG("0000003", "something.went.wrong", AlertType.ERROR);

    private final AlertCode alertCode;

    TechnicalAlertCode(final String code, final String label, final AlertType alertType) {
        alertCode = new AlertCode(code, label, alertType);
    }

    /**
     * get ErrorCode by label
     *
     * @param label
     * @return ErrorCode
     */
    public static Optional<TechnicalAlertCode> findByLabel(String label) {
        for (TechnicalAlertCode err : TechnicalAlertCode.values()) {
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
