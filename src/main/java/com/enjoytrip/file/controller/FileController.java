package com.enjoytrip.file.controller;

import com.enjoytrip.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    private final FileService fileService;
    @Value("${cloud.aws.s3.baseurl}")
    String originPath;
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart(name="file") MultipartFile file) throws IOException {

        String url=fileService.uploadFile(file);
        return new ResponseEntity<String>(originPath+url, HttpStatus.OK);
    }
}
