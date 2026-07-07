package com.thinkconstructive.book_store.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserAlreadyExistsException Tests")
class UserAlreadyExistsExceptionTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Constructor with userName produces correct message
     * - Expected: Message is "User already exists with username: {userName}"
     * - Coverage Target: Constructor logic, message formatting
     */
    @Test
    @DisplayName("Should set correct message with username")
    void testConstructorSetsMessage() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("john");
        assertEquals("User already exists with username: john", ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Instance is a RuntimeException
     * - Expected: instanceof RuntimeException returns true
     * - Coverage Target: Class hierarchy
     */
    @Test
    @DisplayName("Should be a RuntimeException")
    void testIsRuntimeException() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("test");
        assertInstanceOf(RuntimeException.class, ex);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing null username
     * - Expected: Message is "User already exists with username: null"
     * - Coverage Target: Edge case - null input
     */
    @Test
    @DisplayName("Should handle null username")
    void testConstructorWithNullUsername() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException(null);
        assertEquals("User already exists with username: null", ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing empty string username
     * - Expected: Message is "User already exists with username: "
     * - Coverage Target: Edge case - empty input
     */
    @Test
    @DisplayName("Should handle empty username")
    void testConstructorWithEmptyUsername() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("");
        assertEquals("User already exists with username: ", ex.getMessage());
    }
}
