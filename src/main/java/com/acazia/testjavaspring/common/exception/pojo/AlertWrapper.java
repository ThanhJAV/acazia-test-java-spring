package com.acazia.testjavaspring.common.exception.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlertWrapper {

    private int status;

    private String message;

    private boolean error = true;

    @JsonProperty("alerts")
    private Set<Alert> alerts;

}
