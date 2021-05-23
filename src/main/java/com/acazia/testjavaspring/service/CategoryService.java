package com.acazia.testjavaspring.service;

import com.acazia.testjavaspring.common.exception.ValidationException;
import com.acazia.testjavaspring.common.exception.constant.ValidationAlertCode;
import com.acazia.testjavaspring.common.exception.pojo.AlertMessages;
import com.acazia.testjavaspring.common.request.CreateCategoryRequest;
import com.acazia.testjavaspring.common.request.UpdateCategoryRequest;
import com.acazia.testjavaspring.common.response.MessageResponse;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public MessageResponse createCategory(CreateCategoryRequest payload) {

        Optional<Category> optionalCategory = categoryRepository.findByTag(payload.getTag());
        if (optionalCategory.isPresent()) {
            logger.info("Category already exists !", payload.getTag());
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.CATEGORY_ALREADY_EXISTS));
        }

        Category category = Category.builder()
                .name(payload.getName())
                .tag(payload.getTag()).build();
        categoryRepository.save(category);
        List<Product> products = payload.getProducts();
        if (products != null && products.size() > 0) {
            for (Product p : products) {
                Product product = addProductForCategory(category.getName(), p);
                if (StringUtils.isEmpty(product)) {
                    categoryRepository.delete(category);
                    throw new ValidationException(AlertMessages.alert(ValidationAlertCode.UNSUCCESSFUL));
                }
            }
        }

        MessageResponse<Category> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Create category success !");
        messageResponse.setData(category);

        return messageResponse;

    }

    public Page<Category> getCategories(Integer page, Integer size) {

        if (page == null || page <= 0) {
            page = 0;
        }
        if (size == null || size <= 0) {
            size = 30;
        }
        Pageable pageable = PageRequest.of(page, size);

        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories;
    }

    public MessageResponse updateCategory(String tag, UpdateCategoryRequest updateCategoryRequest) {

        Optional<Category> category = categoryRepository.findByTag(tag);
        if (!category.isPresent()) {
            logger.info("Category ID {} not found !", tag);
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.CATEGORIES_DOESNT_EXIST));
        }
        Category categoryUpdate = category.get();
        categoryUpdate.setName(updateCategoryRequest.getName());
        categoryRepository.save(categoryUpdate);

        MessageResponse<Category> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Update category success !");
        messageResponse.setData(categoryUpdate);

        return messageResponse;
    }

    public MessageResponse deleteCategory(String tag) {

        Optional<Category> category = categoryRepository.findByTag(tag);
        if (!category.isPresent()) {
            logger.info("Category tag {} not found !", tag);
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.CATEGORIES_DOESNT_EXIST));
        }
        Category category1 = category.get();

        categoryRepository.delete(category1);

        MessageResponse<Category> messageResponse = new MessageResponse<>();
        messageResponse.setStatus(HttpStatus.OK.value());
        messageResponse.setMessage("Delete category success !");

        return messageResponse;

    }

    private Product addProductForCategory(String name, Product payload) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(payload)) {
            return null;
        }

        Optional<Product> product = productRepository.findByName(payload.getName());
        if (product.isPresent()) {
            throw new ValidationException(AlertMessages.alert(ValidationAlertCode.PRODUCT_ILLEGAL));
        }
        Product product1 = new Product();
        product1.setName(payload.getName());
        product1.setPrice(payload.getPrice());
        product1.setCategoryTag(payload.getCategoryTag());
        productRepository.save(product1);
        return product1;
    }
}
