package com.plantapp.plantapp.contact.controller;

import com.plantapp.plantapp.contact.model.ContactMessage;
import com.plantapp.plantapp.contact.model.ContactRequestDTO;
import com.plantapp.plantapp.contact.service.ContactMessageService;
import com.plantapp.plantapp.contact.service.RecaptchaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactMessageController {
    private final ContactMessageService contactMessageService;
    private final RecaptchaService recaptchaService;

    public ContactMessageController(ContactMessageService contactMessageService, RecaptchaService recaptchaService) {
        this.contactMessageService = contactMessageService;
        this.recaptchaService = recaptchaService;
    }

    @GetMapping()
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        try {
            List<ContactMessage> messages = contactMessageService.getAllMessages();
            return ResponseEntity.ok(messages);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/submit-message")
    public ResponseEntity<ContactMessage> addNewContactMessage(@RequestBody ContactRequestDTO contactRequest) {
        boolean response = recaptchaService.verifyRecaptcha(contactRequest.getRecaptcha());
        if (response) {
            return ResponseEntity.ok(contactMessageService.addNewContactMessage(contactRequest));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
