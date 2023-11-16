package com.plantapp.plantapp.contact.service;

import com.plantapp.plantapp.contact.model.ContactMessage;
import com.plantapp.plantapp.contact.model.ContactRequestDTO;

import java.util.List;

public interface IContactMessageService {
    ContactMessage addNewContactMessage(ContactRequestDTO contactRequest);

    List<ContactMessage> getAllMessages();
}