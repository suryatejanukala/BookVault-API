package com.thinkconstructive.book_store.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookNotFoundException Tests")
class BookNotFoundExceptionTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Constructor with bookId produces correct message
     * - Expected: Message is "Book not found with id: {bookId}"
     * - Coverage Target: Constructor logic, message formatting
     */
    @Test
    @DisplayName("Should set correct message with bookId")
    void testConstructorSetsMessage() {
        BookNotFoundException ex = new BookNotFoundException("123");
        assertEquals("Book not found with id: 123", ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Instance is a RuntimeException
     * - Expected: BookNotFoundException extends RuntimeException
     * - Coverage Target: Class hierarchy
     */
    @Test
    @DisplayName("Should be a RuntimeException")
    void testIsRuntimeException() {
        BookNotFoundException ex = new BookNotFoundException("abc");
        assertInstanceOf(RuntimeException.class, ex);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing null bookId
     * - Expected: Message is "Book not found with id: null"
     * - Coverage Target: Null input handling
     */
    @Test
    @DisplayName("Should handle null bookId")
    void testConstructorWithNullId() {
        BookNotFoundException ex = new BookNotFoundException(null);
        assertEquals("Book not found with id: null", ex.getMessage());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: Constructor
     * - Scenario: Passing empty string bookId
     * - Expected: Message is "Book not found with id: "
     * - Coverage Target: Empty input handling
     */
    @Test
    @DisplayName("Should handle empty bookId")
    void testConstructorWithEmptyId() {
        BookNotFoundException ex = new BookNotFoundException("");
        assertEquals("Book not found with id: ", ex.getMessage());
    }
}
