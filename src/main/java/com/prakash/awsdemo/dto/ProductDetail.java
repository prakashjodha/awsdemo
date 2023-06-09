package com.prakash.awsdemo.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "productDetail")
public class ProductDetail {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String productId;
    @DynamoDBAttribute
    private String productName;
    @DynamoDBAttribute
    private String category;
    @DynamoDBAttribute
    private String description;
    @DynamoDBAttribute
    private long quantity;
}
