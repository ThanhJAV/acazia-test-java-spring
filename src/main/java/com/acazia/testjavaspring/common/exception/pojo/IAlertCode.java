package com.acazia.testjavaspring.common.exception.pojo;


import com.acazia.testjavaspring.common.exception.constant.AlertType;

public interface IAlertCode {
    public String getCode();

    public String getLabel();

    public AlertType getType();
}
