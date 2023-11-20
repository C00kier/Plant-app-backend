package com.plantapp.plantapp.contact.service;

import com.plantapp.plantapp.contact.model.ContactMessage;
import com.plantapp.plantapp.contact.model.ContactRequestDTO;
import com.plantapp.plantapp.contact.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService implements IContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public ContactMessage addNewContactMessage(ContactRequestDTO contactRequest) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setFirstName(contactRequest.getFirstName());
        contactMessage.setEmail(contactRequest.getEmail());
        contactMessage.setGender(contactRequest.getGender());
        contactMessage.setMessage(contactRequest.getMessage());
        return contactMessageRepository.save(contactMessage);
    }

    @Override
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
}