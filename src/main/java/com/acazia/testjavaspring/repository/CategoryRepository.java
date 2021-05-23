package com.acazia.testjavaspring.repository;

import com.acazia.testjavaspring.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

    @Query(value = "select c from Category c where c.tag = :tag")
    Optional<Category> findByTag(@Param("tag") String tag);

}
