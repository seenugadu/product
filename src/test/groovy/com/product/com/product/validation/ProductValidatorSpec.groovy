package com.product.com.product.validation

import com.product.dao.Product
import com.product.validation.ProductValidator
import org.springframework.validation.BindException
import spock.lang.Specification

/**
 * Created by associate on 4/25/17.
 */
class ProductValidatorSpec extends Specification{

    ProductValidator productValidator

    BindException bindException

    Product product

    void setup(){
        productValidator = new ProductValidator()
        product = new Product();
        bindException = new BindException(product, "product");
    }

    def "Validate SKU"(){

        given:
            product.setSku(0)
            product.setName("Demo")

        when:
            productValidator.validate(product,bindException)

        then:
            bindException.getAllErrors().stream().anyMatch({error -> error.getCode().equalsIgnoreCase("5002")}) == true

    }

    def "Validate Product Name"(){

        given:
            product.setSku(110)
            product.setName("2323#%%%%%%")

        when:
            productValidator.validate(product,bindException)

        then:
            bindException.getAllErrors().stream().anyMatch({error -> error.getCode().equalsIgnoreCase("5003")}) == true

    }

}
