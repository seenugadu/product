package com.product.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by associate on 4/16/17.
 */
@Entity
public class Product {

    @Id
    private Integer sku;

    private String name;

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
