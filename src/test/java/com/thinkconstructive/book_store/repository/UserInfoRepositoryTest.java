package com.thinkconstructive.book_store.repository;

import com.thinkconstructive.book_store.entity.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * AI-GENERATED TEST CASE
 *
 * Test Plan Details:
 * - Class: UserInfoRepository
 * - Type: Interface (mock-based contract test)
 * - Coverage Target: Verify contract behavior via mocking
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("UserInfoRepository Tests")
class UserInfoRepositoryTest {

    @Mock
    private UserInfoRepository userInfoRepository;

    @Test
    @DisplayName("Should find user by username successfully")
    void testFindByUserNameSuccess() {
        // given
        UserInfo userInfo = new UserInfo("testuser", "password123", "ROLE_USER");
        when(userInfoRepository.findByUserName("testuser")).thenReturn(Optional.of(userInfo));

        // when
        Optional<UserInfo> result = userInfoRepository.findByUserName("testuser");

        // then
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUserName());
        verify(userInfoRepository).findByUserName("testuser");
    }

    @Test
    @DisplayName("Should save user successfully")
    void testSaveUserSuccess() {
        // given
        UserInfo userInfo = new UserInfo("newuser", "password123", "ROLE_USER");
        when(userInfoRepository.save(userInfo)).thenReturn(userInfo);

        // when
        UserInfo result = userInfoRepository.save(userInfo);

        // then
        assertNotNull(result);
        assertEquals("newuser", result.getUserName());
        verify(userInfoRepository).save(userInfo);
    }

    @Test
    @DisplayName("Should return empty Optional when user not found")
    void testFindByUserNameNotFound() {
        // given
        when(userInfoRepository.findByUserName("nonexistent")).thenReturn(Optional.empty());

        // when
        Optional<UserInfo> result = userInfoRepository.findByUserName("nonexistent");

        // then
        assertTrue(result.isEmpty());
        verify(userInfoRepository).findByUserName("nonexistent");
    }

    @Test
    @DisplayName("Should return empty Optional for empty string username")
    void testFindByUserNameEmptyString() {
        // given
        when(userInfoRepository.findByUserName("")).thenReturn(Optional.empty());

        // when
        Optional<UserInfo> result = userInfoRepository.findByUserName("");

        // then
        assertTrue(result.isEmpty());
        verify(userInfoRepository).findByUserName("");
    }

    @Test
    @DisplayName("Should handle null username")
    void testFindByUserNameNull() {
        // given
        when(userInfoRepository.findByUserName(null)).thenThrow(new IllegalArgumentException("Username cannot be null"));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userInfoRepository.findByUserName(null));
        verify(userInfoRepository).findByUserName(null);
    }
}
