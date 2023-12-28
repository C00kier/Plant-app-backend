package com.plantapp.plantapp.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantapp.plantapp.authentication.AuthenticationResponse;
import com.plantapp.plantapp.authentication.RegisterRequest;
import com.plantapp.plantapp.quiz.QuizService.QuizService;
import com.plantapp.plantapp.quiz.model.Quiz;
import com.plantapp.plantapp.quiz.repository.QuizRepository;
import com.plantapp.plantapp.user.model.User;
import com.plantapp.plantapp.user.repository.UserRepository;
import com.plantapp.plantapp.user_game_progress.model.UserGameProgress;
import com.plantapp.plantapp.user_game_progress.repository.UserGameProgressRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.AssertionErrors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class QuizControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserGameProgressRepository userGameProgressRepository;

    @Autowired
    private QuizService quizService;
    private User testUser;

    @BeforeEach
    void setUp() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("testPassword");

        AuthenticationResponse authenticationResponse = registerUser(registerRequest);
        testUser = userRepository.findById(authenticationResponse.getUserId()).orElse(null);
    }

    @AfterEach
    void tearDown() {
        if (testUser != null) {
            Optional<UserGameProgress> userGameProgress = userGameProgressRepository.findByUser(testUser);
            userGameProgress.ifPresent(gameProgress -> userGameProgressRepository.deleteById(gameProgress.getId()));
            userRepository.deleteById(testUser.getUserId());
        }
    }
    @Test
    void getResult() throws Exception {
        boolean toxicity = true;
        int sun = 1;
        boolean airPurifying = true;
        double matureSize = 1;
        int careDifficulty = 1;

        ResultActions resultActions = mockMvc.perform(put("/quiz/set-quiz-result")
                .param("isToxic", String.valueOf(toxicity))
                .param("isSunny", String.valueOf(sun))
                .param("isAirPurifying", String.valueOf(airPurifying))
                .param("matureSize", String.valueOf(matureSize))
                .param("difficulty", String.valueOf(careDifficulty))
                .param("userId", String.valueOf(testUser.getUserId()))
        );

        resultActions.andExpect(status().isOk())
                .andExpect(content().string("New quiz result created"));

        Quiz quiz = quizRepository.findByUserId(testUser.getUserId());
        assertNotNull("Quiz should not be null", quiz);
        assertEquals("Care difficulty does not match", careDifficulty, quiz.getCare_difficulty());
        assertEquals("Toxicity does not match", toxicity, quiz.isToxicity());
        assertEquals("Sun value does not match", sun, quiz.getSun());
        assertEquals("Air purifying value does not match", airPurifying, quiz.isAir_purifying());
        assertEquals("Mature size does not match", matureSize, quiz.getMature_size());
        assertEquals("User ID does not match", testUser.getUserId(), quiz.getUserId());
    }

    @Test
    void getUserQuiz() throws Exception {
        quizService.createNewQuizRecord(testUser.getUserId(), true,1,true,1,1);
        Quiz quiz = quizRepository.findByUserId(testUser.getUserId());
        System.out.println("Quiz retrieved from the database: " + quiz);

        ResultActions resultActions = mockMvc.perform(get("/quiz/get-quiz-result")
                .param("userId", String.valueOf(testUser.getUserId())));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quizId", notNullValue()))
                .andExpect(jsonPath("$.air_purifying", is(quiz.isAir_purifying())))
                .andExpect(jsonPath("$.toxicity", is(quiz.isToxicity())))
                .andExpect(jsonPath("$.care_difficulty", is(quiz.getCare_difficulty())))
                .andExpect(jsonPath("$.sun", is(quiz.getSun())))
                .andExpect(jsonPath("$.mature_size", is(quiz.getMature_size())))
                .andExpect(jsonPath("$.userId", is(testUser.getUserId())));


    }

    private AuthenticationResponse registerUser(RegisterRequest request) throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), AuthenticationResponse.class);
    }
}