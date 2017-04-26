package com.product.service

import com.product.dao.Product
import com.product.repository.ProductRepository
import spock.lang.Specification
/**
 * Created by associate on 4/16/17.
 */
class ProductServiceSpec extends Specification{


    private ProductService productService;

    private ProductRepository repository;


    def setup(){
        repository = Mock()
        productService = new ProductServiceImpl(repository: repository)
    }

    def "get all products"(){

        given: " A product Hammer"
            def product1 = new Product(sku: 123, name: "Hammer")
            def product2 = new Product(sku: 234, name: "Ceiling Fan")
            List<Product> productList = new ArrayList<>()
            productList.add(product1)
            productList.add(product2)

        when:
            def result = productService.getProducts()

        then: "Product added"
            repository.findAll() >> productList
            productList != null
            productList.size() == 2

    }

    def "add a product"(){

        given: " A product Hammer"
            def product = new Product(sku: 123, name: "Hammer")

        when:
            def result = productService.addProduct(product)

        then: "Product added"
            repository.save(_ as Product) >> product
            result != null
            result.sku == 123
            result.name == 'Hammer'

    }

    def "get a product by sku"(){

    }

    def "delete a product"(){

    }
}
