package com.acazia.testjavaspring.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Product")
@Table(name = "product")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(unique = true, nullable = false)
    private String name;

    private String categoryTag;

    private Double price;


}
