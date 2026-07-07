package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.entity.UserInfo;
import com.thinkconstructive.book_store.repository.UserInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserInfoUserDetailsServiceImpl Tests")
class UserInfoUserDetailsServiceImplTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private UserInfoUserDetailsServiceImpl userInfoUserDetailsService;

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: loadUserByUsername
     * - Scenario: User found in repository, returns UserDetails via UserInfoUserDetailsMapper
     * - Expected: Returns UserDetails with correct username, password, and authorities
     * - Coverage Target: Happy path — lines 23-25, map branch
     */
    @Test
    @DisplayName("Should return UserDetails when user is found")
    void testLoadUserByUsernameSuccess() {
        UserInfo userInfo = new UserInfo("testuser", "encodedPass", "ROLE_USER,ROLE_ADMIN");
        when(userInfoRepository.findByUserName("testuser")).thenReturn(Optional.of(userInfo));

        UserDetails result = userInfoUserDetailsService.loadUserByUsername("testuser");

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("encodedPass", result.getPassword());
        assertEquals(2, result.getAuthorities().size());
        verify(userInfoRepository).findByUserName("testuser");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: loadUserByUsername
     * - Scenario: Empty username passed to repository
     * - Expected: Repository returns empty, throws UsernameNotFoundException
     * - Coverage Target: Edge case — empty string input, orElseThrow branch
     */
    @Test
    @DisplayName("Should throw UsernameNotFoundException for empty username")
    void testLoadUserByUsernameEmptyString() {
        when(userInfoRepository.findByUserName("")).thenReturn(Optional.empty());

        UsernameNotFoundException ex = assertThrows(UsernameNotFoundException.class,
                () -> userInfoUserDetailsService.loadUserByUsername(""));

        assertTrue(ex.getMessage().contains(""));
        verify(userInfoRepository).findByUserName("");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: loadUserByUsername
     * - Scenario: User not found in repository
     * - Expected: Throws UsernameNotFoundException with message containing username
     * - Coverage Target: Error path — orElseThrow branch at line 25
     */
    @Test
    @DisplayName("Should throw UsernameNotFoundException when user not found")
    void testLoadUserByUsernameNotFound() {
        when(userInfoRepository.findByUserName("unknown")).thenReturn(Optional.empty());

        UsernameNotFoundException ex = assertThrows(UsernameNotFoundException.class,
                () -> userInfoUserDetailsService.loadUserByUsername("unknown"));

        assertEquals("User unknown Not Found", ex.getMessage());
        verify(userInfoRepository).findByUserName("unknown");
    }
}
