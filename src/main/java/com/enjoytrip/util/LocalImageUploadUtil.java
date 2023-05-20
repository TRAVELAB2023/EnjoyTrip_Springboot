package com.enjoytrip.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class LocalImageUploadUtil implements ImageUploadUtil {

    private final String path = "src/main/resources/file/image/";
    @Override
    public String uploadFile(MultipartFile file, String dir, String fileName) throws IOException {
        String extension = file.getOriginalFilename().substring(fileName.lastIndexOf("."), fileName.length());
        File folder = new File(path + dir);
        if (!folder.exists()) {
            folder.mkdir();
        }

        UUID uuid = UUID.randomUUID();


        String filePath = new StringBuilder().append(dir).append("/").append(uuid).append(extension).toString();
        file.transferTo(new File( path+filePath));
        return filePath;
    }
}
