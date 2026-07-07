package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.entity.UserInfo;
import com.thinkconstructive.book_store.exception.UserAlreadyExistsException;
import com.thinkconstructive.book_store.exception.UserNotFoundException;
import com.thinkconstructive.book_store.repository.UserInfoRepository;
import com.thinkconstructive.book_store.service.JWTService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserInfoServiceImpl Tests")
class UserInfoServiceImplTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUser
     * - Scenario: New user saved with encoded password, returns HTTP 201
     * - Expected: ResponseStructure with status 201, user created message
     * - Coverage Target: Happy path - lines 39-50
     */
    @Test
    @DisplayName("Should create user successfully with encoded password")
    void testCreateUserSuccess() {
        UserInfoDto dto = new UserInfoDto("testuser", "password123", "ROLE_USER");
        when(userInfoRepository.findByUserName("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userInfoRepository.save(any(UserInfo.class))).thenAnswer(i -> i.getArgument(0));

        ResponseStructure result = userInfoService.createUser(dto);

        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
        assertEquals("User created successfully", result.getMessage());
        assertNotNull(result.getData());
        assertNotNull(result.getDateTime());
        verify(userInfoRepository).findByUserName("testuser");
        verify(passwordEncoder).encode("password123");
        verify(userInfoRepository).save(any(UserInfo.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUser
     * - Scenario: Verify password is encoded (not plaintext) before save
     * - Expected: Saved entity has encoded password, not the original
     * - Coverage Target: Password encoding logic at line 43
     */
    @Test
    @DisplayName("Should encode password before saving user")
    void testCreateUserPasswordEncoded() {
        UserInfoDto dto = new UserInfoDto("newuser", "rawpass", "ROLE_ADMIN");
        when(userInfoRepository.findByUserName("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("rawpass")).thenReturn("$2a$encoded");
        when(userInfoRepository.save(any(UserInfo.class))).thenAnswer(i -> {
            UserInfo saved = i.getArgument(0);
            assertEquals("$2a$encoded", saved.getPassword());
            return saved;
        });

        userInfoService.createUser(dto);

        verify(passwordEncoder).encode("rawpass");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createUser
     * - Scenario: Duplicate username throws UserAlreadyExistsException
     * - Expected: UserAlreadyExistsException thrown
     * - Coverage Target: Duplicate check branch at line 40
     */
    @Test
    @DisplayName("Should throw UserAlreadyExistsException for duplicate username")
    void testCreateUserAlreadyExists() {
        UserInfoDto dto = new UserInfoDto("existinguser", "password", "ROLE_USER");
        when(userInfoRepository.findByUserName("existinguser")).thenReturn(Optional.of(new UserInfo()));

        assertThrows(UserAlreadyExistsException.class, () -> userInfoService.createUser(dto));
        verify(userInfoRepository).findByUserName("existinguser");
        verify(userInfoRepository, never()).save(any());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: Valid credentials authenticated, JWT token returned with HTTP 200
     * - Expected: ResponseStructure with status 200 and JWT token
     * - Coverage Target: Happy path - lines 54-61
     */
    @Test
    @DisplayName("Should authenticate user and return JWT token")
    void testGetUserInfoSuccess() {
        UserInfoDto dto = new UserInfoDto("testuser", "password123", "ROLE_USER");
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtService.generateToken("testuser")).thenReturn("jwt-token-123");

        ResponseStructure result = userInfoService.getUserInfo(dto);

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Authentication successful", result.getMessage());
        assertEquals("jwt-token-123", result.getData());
        assertNotNull(result.getDateTime());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken("testuser");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: AuthenticationManager throws exception for bad credentials
     * - Expected: BadCredentialsException propagates
     * - Coverage Target: Exception path when authenticate() throws
     */
    @Test
    @DisplayName("Should propagate exception when authentication fails")
    void testGetUserInfoAuthenticationFailed() {
        UserInfoDto dto = new UserInfoDto("baduser", "wrongpass", "ROLE_USER");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        assertThrows(BadCredentialsException.class, () -> userInfoService.getUserInfo(dto));
        verify(jwtService, never()).generateToken(anyString());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUserInfo
     * - Scenario: authentication.isAuthenticated() returns false, throws UserNotFoundException
     * - Expected: UserNotFoundException thrown with appropriate message
     * - Coverage Target: False branch at line 55, line 63
     */
    @Test
    @DisplayName("Should throw UserNotFoundException when not authenticated")
    void testGetUserInfoNotAuthenticated() {
        UserInfoDto dto = new UserInfoDto("testuser", "password", "ROLE_USER");
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);

        UserNotFoundException ex = assertThrows(UserNotFoundException.class, () -> userInfoService.getUserInfo(dto));
        assertTrue(ex.getMessage().contains("testuser"));
        verify(jwtService, never()).generateToken(anyString());
    }
}
