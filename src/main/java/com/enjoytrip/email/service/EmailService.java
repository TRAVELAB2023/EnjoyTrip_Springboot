package com.enjoytrip.email.service;


import com.enjoytrip.email.dto.EmailRequestDto;

import javax.servlet.http.HttpSession;

public interface EmailService {
    void sendEmail(EmailRequestDto emailRequestDto);

    void checkEmail(String key);
}
