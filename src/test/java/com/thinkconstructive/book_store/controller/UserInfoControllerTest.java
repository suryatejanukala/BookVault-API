package com.thinkconstructive.book_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.exception.GlobalExceptionHandler;
import com.thinkconstructive.book_store.exception.UserAlreadyExistsException;
import com.thinkconstructive.book_store.exception.UserNotFoundException;
import com.thinkconstructive.book_store.service.UserInfoService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserInfoController Tests")
class UserInfoControllerTest {

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private UserInfoController userInfoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userInfoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    private ResponseStructure buildResponse(int status, String message, Object data) {
        return ResponseStructure.builder()
                .status(status)
                .message(message)
                .data(data)
                .dateTime(LocalDateTime.now())
                .build();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUserInfo
     * - Scenario: POST /user-info/register with valid DTO returns 201
     * - Expected: Returns ResponseStructure with CREATED status
     * - Coverage Target: Happy path for createUserInfo method
     */
    @Test
    @DisplayName("Should register user successfully with valid DTO")
    void testCreateUserInfoSuccess() throws Exception {
        UserInfoDto dto = new UserInfoDto("testuser", "password123", "ROLE_USER");
        ResponseStructure response = buildResponse(201, "User registered successfully", null);
        when(userInfoService.createUser(any(UserInfoDto.class))).thenReturn(response);

        mockMvc.perform(post("/user-info/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("User registered successfully"));

        verify(userInfoService).createUser(any(UserInfoDto.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: POST /user-info/login with valid credentials returns 200 with JWT
     * - Expected: Returns ResponseStructure with OK status and JWT token
     * - Coverage Target: Happy path for getUserInfo method
     */
    @Test
    @DisplayName("Should login user successfully with valid credentials")
    void testGetUserInfoSuccess() throws Exception {
        UserInfoDto dto = new UserInfoDto("testuser", "password123", "ROLE_USER");
        ResponseStructure response = buildResponse(200, "Login successful", "jwt-token-here");
        when(userInfoService.getUserInfo(any(UserInfoDto.class))).thenReturn(response);

        mockMvc.perform(post("/user-info/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.data").value("jwt-token-here"));

        verify(userInfoService).getUserInfo(any(UserInfoDto.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUserInfo
     * - Scenario: Invalid @Valid UserInfoDto returns 400
     * - Expected: Validation error response with field errors
     * - Coverage Target: Validation path for createUserInfo (blank username)
     */
    @Test
    @DisplayName("Should return 400 when registering with invalid DTO")
    void testCreateUserInfoInvalidDto() throws Exception {
        UserInfoDto invalidDto = new UserInfoDto("", "", "");

        mockMvc.perform(post("/user-info/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"));

        verifyNoInteractions(userInfoService);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: Invalid @Valid UserInfoDto returns 400
     * - Expected: Validation error response with field errors
     * - Coverage Target: Validation path for getUserInfo (blank password)
     */
    @Test
    @DisplayName("Should return 400 when logging in with invalid DTO")
    void testGetUserInfoInvalidDto() throws Exception {
        UserInfoDto invalidDto = new UserInfoDto("", "", "");

        mockMvc.perform(post("/user-info/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"));

        verifyNoInteractions(userInfoService);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUserInfo
     * - Scenario: Service throws UserAlreadyExistsException
     * - Expected: Returns 409 Conflict with error message
     * - Coverage Target: Error path for createUserInfo when user exists
     */
    @Test
    @DisplayName("Should return 409 when user already exists")
    void testCreateUserInfoAlreadyExists() throws Exception {
        UserInfoDto dto = new UserInfoDto("existinguser", "password123", "ROLE_USER");
        when(userInfoService.createUser(any(UserInfoDto.class)))
                .thenThrow(new UserAlreadyExistsException("existinguser"));

        mockMvc.perform(post("/user-info/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("User already exists with username: existinguser"))
                .andExpect(jsonPath("$.statusCode").value(409));

        verify(userInfoService).createUser(any(UserInfoDto.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: Service throws UserNotFoundException for invalid credentials
     * - Expected: Returns 401 Unauthorized with error message
     * - Coverage Target: Error path for getUserInfo when credentials invalid
     */
    @Test
    @DisplayName("Should return 401 when user not found during login")
    void testGetUserInfoInvalidCredentials() throws Exception {
        UserInfoDto dto = new UserInfoDto("unknownuser", "password123", "ROLE_USER");
        when(userInfoService.getUserInfo(any(UserInfoDto.class)))
                .thenThrow(new UserNotFoundException("Invalid credentials"));

        mockMvc.perform(post("/user-info/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"))
                .andExpect(jsonPath("$.statusCode").value(401));

        verify(userInfoService).getUserInfo(any(UserInfoDto.class));
    }
}
