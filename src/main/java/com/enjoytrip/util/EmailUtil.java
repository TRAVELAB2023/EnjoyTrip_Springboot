package com.enjoytrip.util;


import com.enjoytrip.model.Member;
import com.enjoytrip.model.TempPassword;
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
    private final String pw_domain="http://localhost:8080/check/";
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendPW(TempPassword tempPassword) {

        MimeMessagePreparator preparator= new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("darkped@naver.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(tempPassword.getMember().getEmail()));
                mimeMessage.setSubject("비밀번호 변경메일입니다","UTF-8");
                mimeMessage.setText("변경된 비밀번호는 "+tempPassword.getTempPw()+"입니다. \n" +
                        "비밀번호 변경을 위해서 아래 링크를 들어가주세요\n"
                        +pw_domain+tempPassword.getTempKey(),"UTF-8");
            }
        };

        try {
            this.javaMailSender.send(preparator);
            logger.info("비번 찾기 시도 아이디 : {}",tempPassword.getMember().getEmail());
        }
        catch(MailException mex){
            logger.error("{}",mex.getMessage());
        }



    }

}
