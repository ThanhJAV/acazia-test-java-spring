package com.acazia.testjavaspring.common.pojo;

import com.acazia.testjavaspring.model.Category;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class Product {

    @NotEmpty
    private String name;

    @NotEmpty
    private Category categoryTag;

    @NotEmpty
    private Double price;
}
