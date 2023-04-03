package com.prakash.awsdemo.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.prakash.awsdemo.dao.ProductRepository;
import com.prakash.awsdemo.dto.ProductDetail;
import com.prakash.awsdemo.dto.User;
import com.prakash.awsdemo.service.AWSClientDynamoDB;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AWSClientDynamoDBImpl implements AWSClientDynamoDB {
    @Autowired
    ProductRepository repo;


    @Override
    public void addProduct(ProductDetail product) {
        repo.save(product);
    }

    @Override
    public List<ProductDetail> getProducts() {
        List<ProductDetail> result = new ArrayList<>();
        repo.findAll().forEach(result::add);
        return result;
    }

    @Override
    public ProductDetail getProducts(String productId) {
        Optional<ProductDetail> product=repo.findById(productId);
        if(product.isEmpty())throw new IllegalArgumentException("No product found for "+productId);
        return product.get();
    }

    @Override
    public List<ProductDetail> getProductByCategory(String category) {
        return repo.findByCategory(category);
    }

    @Override
    public List<ProductDetail> getProductByQuantity(long quantity) {
        return repo.findByQuantity(quantity);
    }
}
