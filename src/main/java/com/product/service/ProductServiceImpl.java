package com.product.service;

import com.product.dao.Product;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by associate on 4/16/17.
 */
@Service
public class ProductServiceImpl  implements ProductService{

    @Autowired
    ProductRepository repository;

    @Override
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product getProduct(Integer sku) {
        return repository.findOne(sku);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public void deleteProduct(Integer sku) {
        repository.delete(sku);
    }
}
