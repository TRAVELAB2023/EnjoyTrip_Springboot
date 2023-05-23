package com.enjoytrip.email.service;


import javax.servlet.http.HttpSession;

public interface EmailService {
    void sendEmail(String email, HttpSession session);
}
