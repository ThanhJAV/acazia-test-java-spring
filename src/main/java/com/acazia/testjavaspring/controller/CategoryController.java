package com.acazia.testjavaspring.controller;

import com.acazia.testjavaspring.common.request.CreateCategoryRequest;
import com.acazia.testjavaspring.common.request.UpdateCategoryRequest;
import com.acazia.testjavaspring.common.response.MessageResponse;
import com.acazia.testjavaspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public HttpEntity<Object> createCategory(@RequestBody @Valid CreateCategoryRequest payload) {
        MessageResponse messageResponse = categoryService.createCategory(payload);
        return ResponseEntity.ok(messageResponse);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public HttpEntity<Object> getCategories(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(new MessageResponse(categoryService.getCategories(page, size)));
    }


    @RequestMapping(path = "/{tag}", method = RequestMethod.PUT)
    public HttpEntity<Object> updateOrderItem(@PathVariable("tag") String tag, @Valid @RequestBody UpdateCategoryRequest payload) {
        return ResponseEntity.ok(new MessageResponse(categoryService.updateCategory(tag, payload)));
    }

    @RequestMapping(path = "/{tag}", method = RequestMethod.DELETE)
    public HttpEntity<Object> deleteCategoryById(@PathVariable("tag") String tag) {
        return ResponseEntity.ok(new MessageResponse(categoryService.deleteCategory(tag)));
    }
}



