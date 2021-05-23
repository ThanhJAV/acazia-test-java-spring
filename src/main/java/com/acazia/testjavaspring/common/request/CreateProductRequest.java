package com.acazia.testjavaspring.common.request;

import com.acazia.testjavaspring.model.Category;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateProductRequest {

    private String name;

    private String categoryTag;

    private Double price;
}
