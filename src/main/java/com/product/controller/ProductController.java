package com.product.controller;

import com.product.dao.Product;
import com.product.service.ProductService;
import com.product.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by associate on 4/17/17.
 */
@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductValidator productValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.setValidator(productValidator);
    }

    @RequestMapping(value="addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product addProduct(@Validated @RequestBody Product product){
        return productService.addProduct(product);
    }

    @RequestMapping(value="products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @RequestMapping(value="products/{sku}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product getProduct(@PathVariable Integer sku){
        return productService.getProduct(sku);
    }
}
