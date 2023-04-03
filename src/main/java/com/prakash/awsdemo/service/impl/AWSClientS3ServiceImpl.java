package com.prakash.awsdemo.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.prakash.awsdemo.dto.S3ObjectCreationRequest;
import com.prakash.awsdemo.dto.S3ObjectDetail;
import com.prakash.awsdemo.service.AWSClientS3Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class AWSClientS3ServiceImpl implements AWSClientS3Service {

    @Value("${aws.region}")
    String region;

    Regions clientRegion;

    AmazonS3 s3Client;

    @PostConstruct
    void init(){
        clientRegion = Regions.fromName(region);
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .build();
    }
    @Override
    public void createBucket(String bucketName) {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName, clientRegion.getName())
                .withCannedAcl(CannedAccessControlList.LogDeliveryWrite);
        s3Client.createBucket(createBucketRequest);
    }


    @Override
    public void putObject(S3ObjectCreationRequest obj) throws InterruptedException {
        ObjectMetadata meta = new ObjectMetadata();
        try {
            meta.setContentLength(obj.getInputStream().available());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        meta.setContentType(obj.getContentType());
      /*  TransferManager tm = TransferManagerBuilder.standard()
                .withS3Client(s3Client)
                .build();
        Upload upload = tm.upload(obj.getBucketName(), obj.getKeyName(), obj.getInputStream(),meta);
        System.out.println("Object upload started");

        // Optionally, wait for the upload to finish before continuing.
        upload.waitForCompletion();
        System.out.println("Object upload complete");

       */
       // s3Client.putObject(obj.getBucketName(), obj.getKeyName(), obj.getFile());
        PutObjectRequest putObjectRequest = new PutObjectRequest(obj.getBucketName(), obj.getKeyName(), obj.getInputStream(), meta);
        s3Client.putObject(putObjectRequest);
    }

    @Override
    public List<S3ObjectDetail> getObjects(String bucketName) {
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        if(CollectionUtils.isEmpty(objects)) Collections.emptyList();
        return objects.stream().map(S3ObjectDetail::new).toList();
    }

    @Override
    public InputStream getObject(String bucketName, String keyName) {
        try {
            S3Object o = s3Client.getObject(bucketName, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            return s3is;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        return null;
    }
}
