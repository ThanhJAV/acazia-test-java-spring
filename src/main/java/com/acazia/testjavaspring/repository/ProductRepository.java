package com.acazia.testjavaspring.repository;

import com.acazia.testjavaspring.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(value = "select p from Product p where p.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    @Query(value = "select p.name as name, p.categoryTag as categoryTag, c.name as categoryName, p.price as price from Product p inner join Category c on p.categoryTag = c.tag where c.name like %:keyword% order by p.price desc, p.name asc")
    List<Object[]> findProductByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
