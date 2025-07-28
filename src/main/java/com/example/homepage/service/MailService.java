package com.example.homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        // 👇追加：送信元を明示的に設定（Xserverのメールアドレスにする）
        message.setFrom("info@tenohira-benri.com");

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
