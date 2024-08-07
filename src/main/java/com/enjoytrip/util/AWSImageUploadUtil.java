package com.enjoytrip.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import com.enjoytrip.exception.custom_exception.FileException;
import com.enjoytrip.exception.message.FileExceptionMessage;
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

        try(InputStream inputStream=file.getInputStream()){
            String contentType=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));

            if(!isCorrectContentType(contentType)){
                logger.info("잘못된 파일 타입 : {}",contentType);
                throw new FileException(FileExceptionMessage.WRONG_CONTENT_TYPE);
            }
            String fullFilePath=filePath+"/"+dirname+"/"+filename+contentType;


            logger.debug("파일 경로 : {}",fullFilePath);

            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            amazonS3.putObject(new PutObjectRequest(bucket, fullFilePath, inputStream, objectMetadata));
            return fullFilePath;
        } catch (FileException e) {
            throw e;
        } catch (IOException e) {
            logger.error("파일 저장 도중 에러 : {}",e.getMessage());
            throw new FileException(FileExceptionMessage.DATA_NOT_FOUND);
        }
    }

    private boolean isCorrectContentType(String contentType) {
        if(contentType==null){
            return false;
        }
        if(contentType.equals(".jpeg") || contentType.equals((".png")) || contentType.equals((".jpg"))){
            return true;
        }
        return false;
    }
}
