package com.acazia.testjavaspring.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Category")
@Table(name = "category")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1176214796508686183L;

    private String name;

   @Id
   @Column(unique = true, nullable = false)
    private String tag;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryTag", nullable=true)
    @OrderBy("price desc")
    private List<Product> products;

}
