package com.alkemy.icons.icons.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendWelcomeEmailTo(String to);
}
