package com.thinkconstructive.book_store.mapper;

import com.thinkconstructive.book_store.entity.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserInfoUserDetailsMapper Tests")
class UserInfoUserDetailsMapperTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: UserInfo with one role ("ROLE_USER") produces single GrantedAuthority
     * - Expected: Single authority in the list
     * - Coverage Target: Constructor logic, roles split with single role
     */
    @Test
    @DisplayName("Should map single role correctly")
    void testConstructorSingleRole() {
        UserInfo userInfo = new UserInfo("john", "pass123", "ROLE_USER");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        Collection<? extends GrantedAuthority> authorities = mapper.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: UserInfo with "ROLE_USER,ROLE_ADMIN" produces two GrantedAuthorities
     * - Expected: Two authorities mapped correctly
     * - Coverage Target: Constructor logic, roles split with multiple roles
     */
    @Test
    @DisplayName("Should map multiple roles correctly")
    void testConstructorMultipleRoles() {
        UserInfo userInfo = new UserInfo("admin", "secret", "ROLE_USER,ROLE_ADMIN");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        List<String> roles = mapper.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        assertEquals(2, roles.size());
        assertTrue(roles.contains("ROLE_USER"));
        assertTrue(roles.contains("ROLE_ADMIN"));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getUsername()
     * - Scenario: Returns the userName from UserInfo
     * - Expected: Username matches input
     * - Coverage Target: getUsername() method
     */
    @Test
    @DisplayName("Should return correct username")
    void testGetUsername() {
        UserInfo userInfo = new UserInfo("testuser", "pwd", "ROLE_USER");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        assertEquals("testuser", mapper.getUsername());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getPassword()
     * - Scenario: Returns the password from UserInfo
     * - Expected: Password matches input
     * - Coverage Target: getPassword() method
     */
    @Test
    @DisplayName("Should return correct password")
    void testGetPassword() {
        UserInfo userInfo = new UserInfo("user", "mypassword", "ROLE_USER");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        assertEquals("mypassword", mapper.getPassword());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getAuthorities()
     * - Scenario: Returns correctly mapped list of SimpleGrantedAuthority
     * - Expected: All roles converted to SimpleGrantedAuthority instances
     * - Coverage Target: getAuthorities() method
     */
    @Test
    @DisplayName("Should return authorities as SimpleGrantedAuthority instances")
    void testGetAuthorities() {
        UserInfo userInfo = new UserInfo("user", "pwd", "ROLE_USER,ROLE_ADMIN,ROLE_MANAGER");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        List<String> authorities = mapper.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        assertEquals(3, authorities.size());
        assertEquals("ROLE_USER", authorities.get(0));
        assertEquals("ROLE_ADMIN", authorities.get(1));
        assertEquals("ROLE_MANAGER", authorities.get(2));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: UserInfo with empty roles string throws IllegalArgumentException
     * - Expected: IllegalArgumentException from SimpleGrantedAuthority
     * - Coverage Target: Edge case - empty roles string
     */
    @Test
    @DisplayName("Should throw IllegalArgumentException for empty roles string")
    void testConstructorEmptyRoles() {
        UserInfo userInfo = new UserInfo("user", "pwd", "");

        assertThrows(IllegalArgumentException.class, () -> new UserInfoUserDetailsMapper(userInfo));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled
     * - Scenario: All default UserDetails status methods return true
     * - Expected: All return true (Spring Security defaults)
     * - Coverage Target: All account status methods
     */
    @Test
    @DisplayName("Should return true for all account status defaults")
    void testAccountStatusDefaults() {
        UserInfo userInfo = new UserInfo("user", "pwd", "ROLE_USER");

        UserInfoUserDetailsMapper mapper = new UserInfoUserDetailsMapper(userInfo);

        assertTrue(mapper.isAccountNonExpired());
        assertTrue(mapper.isAccountNonLocked());
        assertTrue(mapper.isCredentialsNonExpired());
        assertTrue(mapper.isEnabled());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing null UserInfo throws NullPointerException
     * - Expected: NullPointerException thrown
     * - Coverage Target: Error scenario - null input
     */
    @Test
    @DisplayName("Should throw NullPointerException for null UserInfo")
    void testConstructorNullUserInfo() {
        assertThrows(NullPointerException.class, () -> new UserInfoUserDetailsMapper(null));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: UserInfo with null roles throws NullPointerException on split
     * - Expected: NullPointerException thrown
     * - Coverage Target: Error scenario - null roles field
     */
    @Test
    @DisplayName("Should throw NullPointerException for null roles")
    void testConstructorNullRoles() {
        UserInfo userInfo = new UserInfo("user", "pwd", null);

        assertThrows(NullPointerException.class, () -> new UserInfoUserDetailsMapper(userInfo));
    }
}
