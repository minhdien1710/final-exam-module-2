package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "type")
public class Type {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "type")
    private Set<Product> products;

    public Type() {
    }

    public Type(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
