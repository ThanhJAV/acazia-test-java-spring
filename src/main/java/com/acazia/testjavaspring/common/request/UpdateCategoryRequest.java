package com.acazia.testjavaspring.common.request;

import com.acazia.testjavaspring.model.Product;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UpdateCategoryRequest {

    @NotEmpty(message = " = name is not empty !")
    private String name;
}
