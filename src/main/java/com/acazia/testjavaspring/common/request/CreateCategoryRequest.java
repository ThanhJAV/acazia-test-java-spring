package com.acazia.testjavaspring.common.request;

import com.acazia.testjavaspring.model.Product;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Valid
public class CreateCategoryRequest {

    @NotEmpty(message = " = name is not empty !")
    private String name;

    @NotEmpty(message = " tag is not empty !")
    private String tag;

    private List<Product> products;

}
