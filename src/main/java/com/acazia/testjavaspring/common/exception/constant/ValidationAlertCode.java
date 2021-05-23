package com.acazia.testjavaspring.common.exception.constant;


import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

import java.util.Optional;

public enum ValidationAlertCode implements IAlertCode {

    CATEGORY_ALREADY_EXISTS("0000001", "category.already.exists", AlertType.ERROR),
    CATEGORIES_DOESNT_EXIST("0000002", "categories.doesn't.exist", AlertType.ERROR),
    PRODUCT_ALREADY_EXISTS("0000003", "product.already.exists", AlertType.ERROR),
    PRODUCT_DOESNT_EXIST("0000004", "product.doesn't.exist", AlertType.ERROR),
    UNSUCCESSFUL("0000005", "unsuccessful", AlertType.ERROR),
    PRODUCT_ILLEGAL("0000006", "product.illegal", AlertType.ERROR);

    private AlertCode alertCode;

    ValidationAlertCode(final String code, final String label, final AlertType alertType) {
        alertCode = new AlertCode(code, label, alertType);
    }

    /**
     * get ErrorCode by label
     *
     * @param label
     * @return ErrorCode
     */
    public static Optional<ValidationAlertCode> findByLabel(String label) {
        for (ValidationAlertCode err : ValidationAlertCode.values()) {
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
