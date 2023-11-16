package com.plantapp.plantapp.contact.service;

import com.plantapp.plantapp.contact.model.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Service
public class RecaptchaService {
    @Value("${recaptcha.secret}")
    private String recaptchaSecret;

    public boolean verifyRecaptcha(String userRecaptchaToken) {
        final String recaptchaVerifyUrl = "https://www.google.com/recaptcha/api/siteverify";

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("secret", recaptchaSecret);
        requestBody.add("response", userRecaptchaToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        RecaptchaResponse verificationResponse = restTemplate.postForObject(recaptchaVerifyUrl, requestEntity, RecaptchaResponse.class);

        System.out.println(requestBody);
        System.out.println(verificationResponse);

        return verificationResponse != null && verificationResponse.success();
    }

}