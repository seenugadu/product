package com.product.controller

import com.product.dao.Product
import com.product.exception.AppExceptionHandler
import com.product.service.ProductService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.CoreMatchers.containsString
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
/**
 * Created by associate on 4/25/17.
 */
class ProductControllerSpec extends Specification{

    private ProductService productService

    private MockMvc mockMvc

    def setup(){
        productService = Mock()
        def controllerTest = new ProductController(productService: productService)
        mockMvc = MockMvcBuilders.standaloneSetup(controllerTest)
                .setControllerAdvice(new AppExceptionHandler()).build()
    }

    def "GET a product for sku"(){

        given: "SKU and product name"
            def sku = 110
            def productName = 'Demo Product'
            def product = new Product(sku: 110, name: "Demo Product")

        when: "Fetch product for sku"

            def result = mockMvc.perform(get("/products/{sku}",sku)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        then: "Mock the service to fetch get inventory for product and store"
            productService.getProduct(sku) >> product

            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.sku").value(sku))
            result.andExpect(content().string(containsString(productName)))

    }

    def "GET all products"(){

        given: "2 products"
            def product1 = new Product(sku: 110, name: "Demo Product1")
            def product2 = new Product(sku: 111, name: "Demo Product2")
            List<Product> products = new ArrayList<>()
            products.add(product1)
            products.add(product2)

        when: "Fetch all products"

            def result = mockMvc.perform(get("/products")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        then: "Mock the service to fetch all products"
            productService.getProducts() >> products

            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.[0].sku").value(110))
            result.andExpect(jsonPath("\$.[0].name").value("Demo Product1"))


    }

    def "ADD a product"(){

        given: "Set the json body request"
            def jsonBody = "{\"sku\":110, \"name\":\"Demo Product\"}"
            def product = new Product(sku: 110, name: "Demo Product")

        when: "Add a product"

        def result = mockMvc.perform(post("/addProduct")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonBody.getBytes()))


        then: "Mock the service to add a product"
            productService.addProduct(_ as Product) >> product

            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.sku").value(110))
            result.andExpect(jsonPath("\$.name").value("Demo Product"))
    }

}
