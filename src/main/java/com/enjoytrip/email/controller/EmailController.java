package com.enjoytrip.email.controller;

import com.enjoytrip.email.dto.EmailRequestDto;
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


    @PostMapping("/recovery")
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailRequestDto emailRequestDto){
        emailService.sendEmail(emailRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/check/{auth}")
    public ResponseEntity<Void> authCheck( @PathVariable String auth){
        emailService.checkEmail(auth);
        return new ResponseEntity(HttpStatus.OK);

    }



}
