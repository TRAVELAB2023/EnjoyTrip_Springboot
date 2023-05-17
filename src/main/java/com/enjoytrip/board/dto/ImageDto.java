package com.enjoytrip.board.dto;

import com.enjoytrip.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageDto {
    String extension;

    String originalExtension;

    public ImageDto(Image image) {
        this.extension = image.getExtension();
        this.originalExtension = image.getOriginalExtension();

    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "extension='" + extension + '\'' +
                ", originalExtension='" + originalExtension + '\'' +
                '}';
    }
}
