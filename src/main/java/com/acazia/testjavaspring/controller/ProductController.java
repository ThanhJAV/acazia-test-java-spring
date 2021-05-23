package com.acazia.testjavaspring.controller;

import com.acazia.testjavaspring.common.request.CreateProductRequest;
import com.acazia.testjavaspring.common.request.UpdateProductionRequest;
import com.acazia.testjavaspring.common.response.MessageResponse;
import com.acazia.testjavaspring.common.response.SearchProduct;
import com.acazia.testjavaspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public HttpEntity<Object> createProduct(@RequestBody @Valid CreateProductRequest payload) {
        MessageResponse messageResponse = productService.createProduct(payload);
        return ResponseEntity.ok(messageResponse);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public HttpEntity<Object> getCategories(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(new MessageResponse(productService.getProducts(page, size)));
    }


    @RequestMapping(path = "/{name}", method = RequestMethod.PUT)
    public HttpEntity<Object> updateProduct(@PathVariable("name") String name, @Valid @RequestBody UpdateProductionRequest payload) {
        return ResponseEntity.ok(new MessageResponse(productService.updateProduct(name, payload)));
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
    public HttpEntity<Object> deleteCategoryById(@PathVariable("name") String name) {
        return ResponseEntity.ok(new MessageResponse(productService.deleteProduct(name)));
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public HttpEntity<Object> searchProduct(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam("keyword") String keyword) {
        List<SearchProduct> searchProductList = productService.searchProduct(page, size, keyword);
        Page<SearchProduct> searchProducts = new PageImpl<>(searchProductList);
        return ResponseEntity.ok(new MessageResponse(searchProducts));
    }
}
