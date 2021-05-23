package com.acazia.testjavaspring.common.exception.constant;



import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

import java.util.Optional;

public enum ApplicationAlertCode implements IAlertCode {
    CLIENT_ERROR("000000", "client.error", AlertType.ERROR), GENERIC_APPLICATION_ERROR("000001",
            "generic.application.error", AlertType.ERROR), REQUIRED_QUERY_PARAMS("000002",
            "required.query.params", AlertType.ERROR), SERVER_ERROR("000003",
            "server.error", AlertType.ERROR), SOMETHING_WENT_WRONG("000004",
            "something.went.wrong", AlertType.ERROR);
    private AlertCode alertCode;

    ApplicationAlertCode(final String c, final String l, final AlertType t) {
        alertCode = new AlertCode(c, l, t);
    }

    /**
     * get ErrorCode by label
     *
     * @param label
     * @return ErrorCode
     */
    public static Optional<ApplicationAlertCode> findByLabel(String label) {
        for (ApplicationAlertCode err : ApplicationAlertCode.values()) {
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
    public AlertType getType() {
        return alertCode.getType();
    }

    @Override
    public String getLabel() {
        return alertCode.getLabel();
    }
}
