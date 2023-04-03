package com.prakash.awsdemo.web;

import com.prakash.awsdemo.dto.S3ObjectCreationRequest;
import com.prakash.awsdemo.dto.S3ObjectDetail;
import com.prakash.awsdemo.service.AWSClientS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class S3Controller {

    @Autowired
    AWSClientS3Service service;
    @PostMapping("/s3/put")
    public ResponseEntity<String> putFile(@RequestParam("file") MultipartFile file, @ModelAttribute S3ObjectCreationRequest req) throws IOException, InterruptedException {
        req.setInputStream(file.getInputStream());
        req.setContentType(file.getContentType());
        service.putObject(req);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/s3/{bucketName}")
    public List<S3ObjectDetail> getAllObjects(@PathVariable String bucketName){
        return service.getObjects(bucketName);
    }

    @GetMapping("/s3/{bucketName}/{keyName}")
    public ResponseEntity<Resource>  getObject(@PathVariable String bucketName, @PathVariable String keyName) throws IOException{

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        InputStream is=service.getObject(bucketName,keyName);
        ByteArrayResource resource = new ByteArrayResource(is.readAllBytes());

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
