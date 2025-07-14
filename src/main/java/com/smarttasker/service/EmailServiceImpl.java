package com.smarttasker.service;


/*
 * @Created 7/6/25
 * @Project smart-tasker
 * @User Kumar Padigeri
 */

import com.smarttasker.port.in.EmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailUseCase {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Smart Tasker <kumarpadigeri@gmail.com>");
        message.setTo(toEmail);
        message.setSubject("Welcome to Smart Tasker!");
        message.setText("Hi " + name + ",\n\nThank you for registering with Smart Tasker!\n\nEnjoy managing your tasks productively.\n\nRegards,\nSmart Tasker Team");
        mailSender.send(message);

    }
}
