package com.enjoytrip.email.controller;

import com.enjoytrip.email.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.xml.ws.Response;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @GetMapping("/recovery/{email}")
    public ResponseEntity<Void> sendEmail(HttpSession session,@PathVariable @Email(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",
            message = "이메일 양식이 맞지 않습니다.") String email){
        emailService.sendEmail(email,session);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/check/{auth}")
    public ResponseEntity<Void> authCheck(HttpSession session, @PathVariable String auth){
        String storeAuth=(String)session.getAttribute("auth");
        if(storeAuth==auth){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }



}
