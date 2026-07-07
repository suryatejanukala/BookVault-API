package com.thinkconstructive.book_store.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserNotFoundException Tests")
class UserNotFoundExceptionTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Constructor with message string sets exception message correctly
     * - Expected: getMessage() returns the exact message passed to constructor
     * - Coverage Target: Constructor logic, line 4-5
     */
    @Test
    @DisplayName("Should set correct message from constructor")
    void testConstructorSetsMessage() {
        UserNotFoundException ex = new UserNotFoundException("User not found");
        assertEquals("User not found", ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Instance is a RuntimeException
     * - Expected: UserNotFoundException extends RuntimeException
     * - Coverage Target: Class hierarchy
     */
    @Test
    @DisplayName("Should be a RuntimeException")
    void testIsRuntimeException() {
        UserNotFoundException ex = new UserNotFoundException("test");
        assertInstanceOf(RuntimeException.class, ex);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing null results in null getMessage()
     * - Expected: getMessage() returns null
     * - Coverage Target: Null input edge case
     */
    @Test
    @DisplayName("Should handle null message")
    void testConstructorWithNullMessage() {
        UserNotFoundException ex = new UserNotFoundException(null);
        assertNull(ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing empty string results in empty getMessage()
     * - Expected: getMessage() returns empty string
     * - Coverage Target: Empty input edge case
     */
    @Test
    @DisplayName("Should handle empty message")
    void testConstructorWithEmptyMessage() {
        UserNotFoundException ex = new UserNotFoundException("");
        assertEquals("", ex.getMessage());
    }
}
