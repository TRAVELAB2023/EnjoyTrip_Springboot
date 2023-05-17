package com.enjoytrip.notice.service;

import com.enjoytrip.exception.custom_exception.RoleException;
import com.enjoytrip.members.dto.RegisterDto;
import com.enjoytrip.members.service.MemberService;
import com.enjoytrip.notice.dto.NoticeRegisterDto;
import com.enjoytrip.util.SearchCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class NoticeServiceImplTest {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("생성 및 조회 테스트")
    void register() throws Exception {
        NoticeRegisterDto noticeRegisterDto = new NoticeRegisterDto("test", 12, "테스트입니다.");
        int noticeId = noticeService.register(noticeRegisterDto);
        String result = noticeService.detail(noticeId).getContent();
        Assertions.assertEquals(result, noticeRegisterDto.getContent());
    }

    @Test
    @DisplayName("관리자 아닌 유저 글 작성 테스트")
    void register_failed() throws Exception {
        RegisterDto registerDto = new RegisterDto("test@test.com", "test", true, "test");
        int n = memberService.join(registerDto);
        NoticeRegisterDto noticeRegisterDto = new NoticeRegisterDto("test", n, "테스트입니다.");
        Assertions.assertThrows(RoleException.class, () -> noticeService.register(noticeRegisterDto));
    }

    @Test
    void noticeList_search_title_contain() throws Exception {

        for (int i = 1; i <= 100; i++) {
            noticeService.register(new NoticeRegisterDto(i + "::testTitle", 12, i * i + "::testContent"));
        }
        SearchCondition searchCondition = new SearchCondition("7", 1);
        PageRequest pageRequest = PageRequest.of(0, 1);
        Assertions.assertTrue(noticeService.noticeList(searchCondition, pageRequest).get(0).getTitle().contains("7"));
    }

}