package com.thinkconstructive.book_store.mapper;

import com.thinkconstructive.book_store.dto.UserInfoDto;
import com.thinkconstructive.book_store.entity.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserInfoMapper Tests")
class UserInfoMapperTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: UserInfo with all fields maps correctly to UserInfoDto
     * - Expected: UserInfoDto contains same userName, password, roles
     * - Coverage Target: toDto method happy path
     */
    @Test
    @DisplayName("Should map UserInfo to UserInfoDto successfully")
    void testToDtoSuccess() {
        UserInfo userInfo = new UserInfo("john", "secret123", "ROLE_USER");

        UserInfoDto dto = UserInfoMapper.toDto(userInfo);

        assertEquals("john", dto.userName());
        assertEquals("secret123", dto.password());
        assertEquals("ROLE_USER", dto.roles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: UserInfoDto with all fields maps correctly to UserInfo
     * - Expected: UserInfo contains same userName, password, roles
     * - Coverage Target: toEntity method happy path
     */
    @Test
    @DisplayName("Should map UserInfoDto to UserInfo successfully")
    void testToEntitySuccess() {
        UserInfoDto dto = new UserInfoDto("john", "secret123", "ROLE_USER");

        UserInfo entity = UserInfoMapper.toEntity(dto);

        assertEquals("john", entity.getUserName());
        assertEquals("secret123", entity.getPassword());
        assertEquals("ROLE_USER", entity.getRoles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: UserInfo with null fields maps to UserInfoDto with null fields
     * - Expected: UserInfoDto fields are null
     * - Coverage Target: toDto with null field values
     */
    @Test
    @DisplayName("Should map UserInfo with null fields to UserInfoDto with null fields")
    void testToDtoWithNullFields() {
        UserInfo userInfo = new UserInfo(null, null, null);

        UserInfoDto dto = UserInfoMapper.toDto(userInfo);

        assertNull(dto.userName());
        assertNull(dto.password());
        assertNull(dto.roles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: UserInfoDto with null fields maps to UserInfo with null fields
     * - Expected: UserInfo fields are null
     * - Coverage Target: toEntity with null field values
     */
    @Test
    @DisplayName("Should map UserInfoDto with null fields to UserInfo with null fields")
    void testToEntityWithNullFields() {
        UserInfoDto dto = new UserInfoDto(null, null, null);

        UserInfo entity = UserInfoMapper.toEntity(dto);

        assertNull(entity.getUserName());
        assertNull(entity.getPassword());
        assertNull(entity.getRoles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: UserInfo with empty string fields maps correctly
     * - Expected: UserInfoDto fields are empty strings
     * - Coverage Target: toDto with empty strings
     */
    @Test
    @DisplayName("Should map UserInfo with empty strings to UserInfoDto with empty strings")
    void testToDtoWithEmptyStrings() {
        UserInfo userInfo = new UserInfo("", "", "");

        UserInfoDto dto = UserInfoMapper.toDto(userInfo);

        assertEquals("", dto.userName());
        assertEquals("", dto.password());
        assertEquals("", dto.roles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: UserInfoDto with empty string fields maps correctly
     * - Expected: UserInfo fields are empty strings
     * - Coverage Target: toEntity with empty strings
     */
    @Test
    @DisplayName("Should map UserInfoDto with empty strings to UserInfo with empty strings")
    void testToEntityWithEmptyStrings() {
        UserInfoDto dto = new UserInfoDto("", "", "");

        UserInfo entity = UserInfoMapper.toEntity(dto);

        assertEquals("", entity.getUserName());
        assertEquals("", entity.getPassword());
        assertEquals("", entity.getRoles());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: Passing null UserInfo throws NullPointerException
     * - Expected: NullPointerException is thrown
     * - Coverage Target: toDto null input error path
     */
    @Test
    @DisplayName("Should throw NullPointerException when UserInfo is null")
    void testToDtoWithNullUserInfo() {
        assertThrows(NullPointerException.class, () -> UserInfoMapper.toDto(null));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: Passing null UserInfoDto throws NullPointerException
     * - Expected: NullPointerException is thrown
     * - Coverage Target: toEntity null input error path
     */
    @Test
    @DisplayName("Should throw NullPointerException when UserInfoDto is null")
    void testToEntityWithNullUserInfoDto() {
        assertThrows(NullPointerException.class, () -> UserInfoMapper.toEntity(null));
    }
}
