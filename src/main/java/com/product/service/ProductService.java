package com.product.service;

import com.product.dao.Product;

import java.util.List;

/**
 * Created by associate on 4/17/17.
 */
public interface ProductService {

    Product addProduct(Product product);

    Product getProduct(Integer sku);

    List<Product> getProducts();

    void deleteProduct(Integer sku);
}
