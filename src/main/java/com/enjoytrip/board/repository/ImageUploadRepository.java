package com.enjoytrip.board.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadRepository {
    String uploadFile(MultipartFile file, String dir,String fileName) throws IOException;
}
