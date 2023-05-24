package com.enjoytrip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private int port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSender javaMailSender() throws Exception {
        JavaMailSenderImpl sender=new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setDefaultEncoding("UTF-8");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable",true);
        properties.put("mail.smtp.ssl.trust","smtp.naver.com");
        properties.put("mail.smtp.starttls.enable",true);

        sender.setJavaMailProperties(properties);
        return sender;
    }
}
