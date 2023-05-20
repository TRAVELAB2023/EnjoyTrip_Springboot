package com.enjoytrip.file;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadTest {

    @Autowired
    MockMvc mockmvc;

    @Test
    @DisplayName("파일 업로드 테스트")
    public void fileUploadTest() throws Exception{
        Path path= Paths.get("src/test/image/img.png");
        System.out.println(path.toString());
        MockMultipartFile file=new MockMultipartFile("file","img.png","png",new FileInputStream(path.toFile()));
        mockmvc.perform(multipart("/upload").file(file))
                .andExpect(status().isOk());;
    }


}
