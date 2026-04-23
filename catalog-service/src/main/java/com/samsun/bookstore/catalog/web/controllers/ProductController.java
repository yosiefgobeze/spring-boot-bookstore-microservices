package com.samsun.bookstore.catalog.web.controllers;

import com.samsun.bookstore.catalog.domain.PagedResult;
import com.samsun.bookstore.catalog.domain.Product;
import com.samsun.bookstore.catalog.domain.ProductNotFoundException;
import com.samsun.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
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
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
