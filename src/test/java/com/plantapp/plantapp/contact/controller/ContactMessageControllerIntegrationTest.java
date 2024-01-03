package com.plantapp.plantapp.contact.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.contact.model.ContactRequestDTO;
import com.plantapp.plantapp.contact.service.RecaptchaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class ContactMessageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecaptchaService recaptchaService;

    @Test
    void addNewContactMessage() throws Exception {
        ContactRequestDTO newContactMessage = new ContactRequestDTO();
        newContactMessage.setMessage("test message");
        newContactMessage.setEmail("test@mail.com");
        newContactMessage.setFirstName("testName");
        newContactMessage.setGender("Pan");
        newContactMessage.setRecaptcha("dummyRecaptchaValue");

        when(recaptchaService.verifyRecaptcha(anyString())).thenReturn(true);

        ResultActions resultActions = mockMvc.perform(post("/contact/submit-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newContactMessage)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.email", is(newContactMessage.getEmail())))
                .andExpect(jsonPath("$.message", is(newContactMessage.getMessage())))
                .andExpect(jsonPath("$.firstName", is(newContactMessage.getFirstName())))
                .andExpect(jsonPath("$.gender", is(newContactMessage.getGender())));

        verify(recaptchaService, times(1)).verifyRecaptcha(anyString());

    }

    @Test
    void getAllMessages() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }
}