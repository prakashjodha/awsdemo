package com.prakash.awsdemo.service;

import com.prakash.awsdemo.dto.ProductDetail;

import java.util.List;

public interface AWSClientDynamoDB {

    void addProduct(ProductDetail product);

    List<ProductDetail> getProducts();

    ProductDetail getProducts(String productId);

    List<ProductDetail> getProductByCategory(String category);

    List<ProductDetail> getProductByQuantity(long quantity);

}
