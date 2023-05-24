package com.enjoytrip.util;


import com.enjoytrip.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Component
public class EmailUtil {
    private Logger logger= LoggerFactory.getLogger(EmailUtil.class);
    private final JavaMailSender javaMailSender;

    /**
     * 비밀번호 찾기 기본 url
     */
    private final String pw_domain="http://localhost:8080";
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendPW(String email, HttpSession session) {

        // 비번을 변경할 수 있는 인증키를 가져온다.
        String auth=storeAuth(session);
        // 세션에 인증키가 해당 아이디를 담게담게 한다
        session.setAttribute(auth, email);

        MimeMessagePreparator preparator= new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("darkped@naver.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                mimeMessage.setSubject("비밀번호 변경메일입니다","UTF-8");
                mimeMessage.setText("비밀번호 변경을 위해서 아래 링크를 들어가주세요\n"
                        +pw_domain+auth,"UTF-8");
            }
        };

        try {
            this.javaMailSender.send(preparator);
            logger.info("비번 찾기 시도 아이디 : {}",email);
        }
        catch(MailException mex){
            logger.error("{}",mex.getMessage());
        }



    }

    private String storeAuth(HttpSession httpSession) {
        String auth=null;
        if(httpSession.getAttribute("auth")!=null) {
            httpSession.removeAttribute((String)httpSession.getAttribute("auth")); // 기존의 인증코드 제거
        }
        auth= UUID.randomUUID().toString();
        httpSession.setAttribute("auth", auth);

        return auth;
    }

}
