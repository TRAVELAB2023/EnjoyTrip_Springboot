package com.enjoytrip.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadUtil {
    String uploadFile(MultipartFile file, String dir,String fileName) throws IOException;
}
