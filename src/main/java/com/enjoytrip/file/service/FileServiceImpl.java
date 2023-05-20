package com.enjoytrip.file.service;

import com.enjoytrip.util.ImageUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    private final ImageUploadUtil imageUploadUtil;

    public FileServiceImpl(ImageUploadUtil imageUploadUtil) {
        this.imageUploadUtil = imageUploadUtil;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName= UUID.randomUUID().toString();
        LocalDate localDate= LocalDate.now();
        imageUploadUtil.uploadFile(file,localDate.toString(),fileName);
        return null;
    }
}
