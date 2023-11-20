package com.plantapp.plantapp.contact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDTO {
    private String firstName;
    private String email;
    private String gender;
    private String message;
    private String recaptcha;
}
