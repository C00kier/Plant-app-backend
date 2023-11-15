package com.plantapp.plantapp.contact.service;

import com.plantapp.plantapp.contact.model.ContactMessage;

import java.util.List;

public interface IContactMessageService {
    ContactMessage addNewContactMessage(ContactMessage contactMessage);

    List<ContactMessage> getAllMessages();
}
