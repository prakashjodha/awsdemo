package com.prakash.awsdemo.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class S3ObjectCreationRequest {
    String bucketName;
    String fileName;

    InputStream inputStream;

    String contentType;

    Long fileLength;
    String keyName;
}
