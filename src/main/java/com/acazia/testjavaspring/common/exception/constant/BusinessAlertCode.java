package com.acazia.testjavaspring.common.exception.constant;


import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

import java.util.Optional;

public enum BusinessAlertCode implements IAlertCode {
    NOT_FOUND("0000001", "not.found", AlertType.ERROR),
    REQUIRED_QUERY_PARAMS("0000002", "required.query.params", AlertType.ERROR),
    PERSON_FORM_HAS_A_NEW_STATUS("0000003", "person.form.has.a.new.status", AlertType.ERROR),
    PERSON_FORM_HAS_A_OFFICIAL_STATUS("0000004", "person.has.a.official.status", AlertType.ERROR),
    PERSON_FORM_HAS_A_CLOSE_STATUS("0000005", "person.has.a.close.status", AlertType.ERROR),
    USER_ALREADY_HAS_AN_OFFICIAL_FORM("0000006", "user.already.has.an.official.form", AlertType.ERROR),
    END_DATE_OR_START_DATE_NOT_VALID("0000007", "end.date.or.start.date.not.valid", AlertType.ERROR),
    APPLICATION_FROM_HAS_CANCEL_STATUS("0000008", "application.form.has.cancel.status", AlertType.ERROR),
    THIS_APPLICATION_FORM_IS_NOT_ALLOWED_TO_UPDATE("0000009", "this.application.form.is.not.allowed.to.update", AlertType.ERROR),
    THIS_APPLICATION_FORM_IS_NOT_ALLOWED_TO_DELETE("0000010", "this.application.form.is.not.allowed.to.delete", AlertType.ERROR),
    INELIGIBLE_FOR_STATUS_UPDATE("0000011", "ineligible.for.status.update", AlertType.ERROR);

    private AlertCode alertCode;

    BusinessAlertCode(final String c, final String l, final AlertType t) {
        alertCode = new AlertCode(c, l, t);
    }

    /**
     * get ErrorCode by label
     *
     * @param label
     * @return ErrorCode
     */
    public static Optional<BusinessAlertCode> findByLabel(String label) {
        for (BusinessAlertCode err : BusinessAlertCode.values()) {
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
