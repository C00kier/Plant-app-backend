package com.plantapp.plantapp.contact.service;

import com.plantapp.plantapp.contact.model.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RecaptchaService {
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    public boolean verifyRecaptcha(String userRecaptchaToken) {
        final String recaptchaVerifyUrl = "https://www.google.com/recaptcha/api/siteverify";
        String requestBody = "secret=" + recaptchaSecret + "&response=" + userRecaptchaToken;
        RestTemplate restTemplate = new RestTemplate();
        RecaptchaResponse verificationResponse = restTemplate.postForObject(recaptchaVerifyUrl, requestBody, RecaptchaResponse.class);

        return verificationResponse != null && verificationResponse.success();
    }

}
