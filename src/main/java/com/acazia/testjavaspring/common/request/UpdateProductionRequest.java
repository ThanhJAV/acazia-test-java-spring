package com.acazia.testjavaspring.common.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class UpdateProductionRequest {

    private String categoryTag;

    private Double price;
}
