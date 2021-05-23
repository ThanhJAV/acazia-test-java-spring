package com.acazia.testjavaspring.common.exception.pojo;


import com.acazia.testjavaspring.common.exception.constant.AlertType;

public class AlertCode {
    private String code;
    private String label;
    private AlertType type;

    public AlertCode(final String code, final String label, final AlertType type) {
        this.code = code;
        this.label = label;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public AlertType getType() {
        return type;
    }
}
