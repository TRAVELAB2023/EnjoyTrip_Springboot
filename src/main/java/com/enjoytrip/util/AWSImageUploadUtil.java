package com.enjoytrip.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class AWSImageUploadUtil implements ImageUploadUtil{
    private final Logger logger= LoggerFactory.getLogger(AWSImageUploadUtil.class);
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.image.path}")
    private String filePath;
    public AWSImageUploadUtil(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFile(MultipartFile file,String dirname, String filename) throws IOException {

        String contentType=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String fullFilePath=filePath+"/"+dirname+"/"+filename+contentType;
//		String fullFilePath=filePath+"/"+dirname+"/"+filename;

        logger.debug("파일 경로 : {}",fullFilePath);

        ObjectMetadata objectMetadata=new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream=file.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(bucket, fullFilePath, inputStream, objectMetadata));
        } catch (IOException e) {
            logger.error("파일 저장 도중 에러 : {}",e.getMessage());
        }
        return fullFilePath;
    }
}
