package com.product.validation;


import com.product.dao.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by associate on 4/20/17.
 */
@Component
public class ProductValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz) ;
    }

    @Override
    public void validate(Object target, Errors errors) {

        Product product = (Product) target;
        validSKU(product.getSku(),errors);
        validateProductName(product.getName(), errors);

    }

    private void validSKU(Integer sku, Errors errors){

        if(NumberUtils.INTEGER_ZERO.compareTo(sku) >= 0){
            errors.reject("5002");
        }
    }

    private void validateProductName(String name, Errors errors){
        if (StringUtils.isBlank(name) || !StringUtils.isAlphanumericSpace(name)) {
            errors.reject("5003");
        }
    }

}
