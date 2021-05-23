package com.acazia.testjavaspring.service;

import com.acazia.testjavaspring.common.Utils;
import com.acazia.testjavaspring.common.exception.ValidationException;
import com.acazia.testjavaspring.common.exception.constant.ValidationAlertCode;
import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import com.acazia.testjavaspring.common.request.CreateProductRequest;
import com.acazia.testjavaspring.common.request.UpdateProductionRequest;
import com.acazia.testjavaspring.common.response.MessageResponse;
import com.acazia.testjavaspring.common.response.SearchProduct;
import com.acazia.testjavaspring.model.Category;
import com.acazia.testjavaspring.model.Product;
import com.acazia.testjavaspring.repository.CategoryRepository;
import com.acazia.testjavaspring.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public MessageResponse createProduct(CreateProductRequest createProductRequest) {

        Optional<Product> optionalProduct = productRepository.findByName(createProductRequest.getName());
        Optional<Category> optionalCategory = categoryRepository.findByTag(createProductRequest.getCategoryTag());
        if (optionalProduct.isPresent()) {
            logger.info("Product already exists !", createProductRequest.getName());
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.PRODUCT_ALREADY_EXISTS));
        }

        if (!optionalCategory.isPresent()) {
            logger.info("Category doesnt exist !", createProductRequest.getCategoryTag());
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.CATEGORIES_DOESNT_EXIST));
        }

        Product product = Product.builder()
                .name(createProductRequest.getName())
                .categoryTag(createProductRequest.getCategoryTag()).price(createProductRequest.getPrice()).build();
        productRepository.save(product);

        MessageResponse<Product> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Create product success !");
        messageResponse.setData(product);

        return messageResponse;
    }

    public Page<Product> getProducts(Integer page, Integer size) {

        if (page == null || page <= 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 30;
        }
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public MessageResponse updateProduct(String name, UpdateProductionRequest payload) {

        Optional<Product> product = productRepository.findByName(name);
        Optional<Category> optionalCategory = categoryRepository.findByTag(payload.getCategoryTag());
        if (!product.isPresent()) {
            logger.info("Product name {} not found !", name);
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.PRODUCT_DOESNT_EXIST));
        }
        if (!optionalCategory.isPresent()) {
            logger.info("Category doesnt exist !", payload.getCategoryTag());
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.CATEGORIES_DOESNT_EXIST));
        }
        Product productUpdate = product.get();
        productUpdate.setPrice(payload.getPrice());
        productUpdate.setCategoryTag(payload.getCategoryTag());
        productRepository.save(productUpdate);

        MessageResponse<Product> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Update product success !");
        messageResponse.setData(productUpdate);

        return messageResponse;
    }

    public MessageResponse deleteProduct(String name) {

        Optional<Product> product = productRepository.findByName(name);
        if (!product.isPresent()) {
            logger.info("Product name {} not found !", name);
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.PRODUCT_DOESNT_EXIST));
        }

        Product products = product.get();
        productRepository.delete(products);

        MessageResponse<Category> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Delete product success !");

        return messageResponse;

    }

    public List<SearchProduct> searchProduct(Integer page, Integer size, String keyword) {
        if (page == null || page <= 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 30;
        }
        Pageable pageable = PageRequest.of(page, size);

        List<Object[]> productList = productRepository.findProductByKeyword(keyword, pageable);

        List<SearchProduct> searchProducts = productList.stream().map(row ->
        {
            SearchProduct searchProduct = new SearchProduct();
            searchProduct.setName(Utils.getProperty(row[0]));
            searchProduct.setCategoryTag(Utils.getProperty(row[1]));
            searchProduct.setCategoryName(Utils.getProperty(row[2]));
            searchProduct.setPrice(Double.parseDouble(Utils.getProperty(row[3])));
            return searchProduct;
        }).collect(Collectors.toList());
        return searchProducts;

    }
}

