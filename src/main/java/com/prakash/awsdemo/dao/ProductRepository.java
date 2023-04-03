package com.prakash.awsdemo.dao;

import com.prakash.awsdemo.dto.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import java.util.List;

@EnableScan
public interface ProductRepository extends CrudRepository<ProductDetail, String> {
    List<ProductDetail> findByCategory(String category);
    List<ProductDetail> findByQuantity(long quantity);

}


