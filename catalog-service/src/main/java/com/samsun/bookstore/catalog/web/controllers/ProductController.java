package com.samsun.bookstore.catalog.web.controllers;

import com.samsun.bookstore.catalog.domain.PagedResult;
import com.samsun.bookstore.catalog.domain.Product;
import com.samsun.bookstore.catalog.domain.ProductNotFoundException;
import com.samsun.bookstore.catalog.domain.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        log.info("Fetching products for page: {}", pageNo);
        return productService.getProducts(pageNo);
    }

    @GetMapping(path = "/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        //    just to demonstrate how the RestClient will Time out when the catalog-service did not respond for
        //    above 5 seconds,
        //    void iAmSleeping(){
        //        try {
        //            Thread.sleep(6000);
        //        } catch (InterruptedException e) {
        //            throw new RuntimeException(e);
        //        }
        //    }
        log.info("Fetching product for code: {}", code);
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
