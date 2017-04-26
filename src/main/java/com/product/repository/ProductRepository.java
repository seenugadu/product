package com.product.repository;

import com.product.dao.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by associate on 4/17/17.
 */

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>{

    Product save(Product product);

    List<Product> findAll();
}
