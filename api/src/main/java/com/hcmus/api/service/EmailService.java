package com.hcmus.api.service;

import com.hcmus.api.service.impl.email.EmailDetail;

public interface EmailService {
    void sendEmail(EmailDetail emailDetail);
}
