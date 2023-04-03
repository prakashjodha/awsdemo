package com.prakash.awsdemo.service;

import com.prakash.awsdemo.dto.S3ObjectCreationRequest;
import com.prakash.awsdemo.dto.S3ObjectDetail;

import java.io.InputStream;
import java.util.List;

public interface AWSClientS3Service {

    void createBucket(String bucketName);

    void putObject(S3ObjectCreationRequest obj) throws InterruptedException;

    List<S3ObjectDetail> getObjects(String bucketName);

    InputStream getObject(String bucketName, String keyName);
}
