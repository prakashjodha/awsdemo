package com.prakash.awsdemo.dto;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.Data;

@Data
public class S3ObjectDetail {

    public S3ObjectDetail(S3ObjectSummary summary){
        this.keyName=summary.getKey();
        this.size=summary.getSize();
        this.storageClass=summary.getStorageClass();
    }
    String keyName;
    long size;

    String storageClass;
}
