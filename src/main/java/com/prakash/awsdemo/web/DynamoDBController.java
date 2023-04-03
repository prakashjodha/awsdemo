package com.prakash.awsdemo.web;

import com.prakash.awsdemo.dto.ProductDetail;
import com.prakash.awsdemo.dto.S3ObjectDetail;
import com.prakash.awsdemo.service.AWSClientDynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class DynamoDBController {
    @Autowired
    AWSClientDynamoDB service;
    @PostMapping("/product")
    public ResponseEntity<String> putProduct(@ModelAttribute ProductDetail req) throws IOException, InterruptedException {
        service.addProduct(req);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/product")
    public List<ProductDetail> getAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/product/{productId}")
    public ProductDetail getProductDetail(@PathVariable String productId){
        return service.getProducts(productId);
    }

    @GetMapping("/product/category/{category}")
    public List<ProductDetail> getProductDetailByCategory(@PathVariable String category){
        return service.getProductByCategory(category);
    }

    @GetMapping("/product/quantity/{quantity}")
    public List<ProductDetail> getProductDetailByQuantity(@PathVariable long quantity){
        return service.getProductByQuantity(quantity);
    }
}
