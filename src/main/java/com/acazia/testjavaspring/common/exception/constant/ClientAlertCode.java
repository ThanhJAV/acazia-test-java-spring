package com.acazia.testjavaspring.common.exception.constant;


import com.acazia.testjavaspring.common.exception.pojo.AlertCode;
import com.acazia.testjavaspring.common.exception.pojo.IAlertCode;

public enum ClientAlertCode implements IAlertCode {

    LDAP_SERVER_ERROR("0000001", "ldap.server.error", AlertType.ERROR),
    SOMETHING_WENT_WRONG("0000002", "something.went.wrong", AlertType.ERROR);
    private AlertCode alertCode;

    ClientAlertCode(final String c, final String l, final AlertType t) {
        alertCode = new AlertCode(c, l, t);
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public AlertType getType() {
        return null;
    }
}
