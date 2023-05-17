package com.enjoytrip.board.service;

import com.enjoytrip.board.dto.BoardRegisterDto;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.FileItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;

    int memberId=0;
    List<MultipartFile> list = new ArrayList<>();
    @BeforeEach
    void before() throws SQLException, IOException {
        memberId=memberService.join(new RegisterDto("test@test", "test", true, "test"));
        File file = new File("src/test/image/img.png");
        DiskFileItem diskFileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()),
                false, file.getName(), (int) file.length(),file.getParentFile());
        InputStream input = new FileInputStream(file);
        OutputStream os = diskFileItem.getOutputStream();
        IOUtils.copy(input, os);
        MultipartFile uploadFile = new CommonsMultipartFile(diskFileItem);

        list.add(uploadFile);
    }

    @Test
    void register() throws IOException {
        BoardRegisterDto boardRegisterDto = new BoardRegisterDto("test",memberId,"test");
        boardService.register(boardRegisterDto, list);

    }
}