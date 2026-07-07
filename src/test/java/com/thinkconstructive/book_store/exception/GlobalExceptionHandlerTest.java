package com.thinkconstructive.book_store.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("GlobalExceptionHandler Tests")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleBookNotFound
     * - Scenario: BookNotFoundException is thrown
     * - Expected: Returns 404 with ErrorResponse containing exception message
     * - Coverage Target: handleBookNotFound method, lines 16-17
     */
    @Test
    @DisplayName("Should return 404 with error message for BookNotFoundException")
    void testHandleBookNotFound() {
        BookNotFoundException ex = new BookNotFoundException("123");

        ResponseEntity<ErrorResponse> response = handler.handleBookNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found with id: 123", response.getBody().message());
        assertEquals(404, response.getBody().statusCode());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleUserNotFound
     * - Scenario: UserNotFoundException is thrown
     * - Expected: Returns 401 with ErrorResponse containing exception message
     * - Coverage Target: handleUserNotFound method, lines 20-22
     */
    @Test
    @DisplayName("Should return 401 with error message for UserNotFoundException")
    void testHandleUserNotFound() {
        UserNotFoundException ex = new UserNotFoundException("User not found");

        ResponseEntity<ErrorResponse> response = handler.handleUserNotFound(ex);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("User not found", response.getBody().message());
        assertEquals(401, response.getBody().statusCode());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleValidationException
     * - Scenario: MethodArgumentNotValidException with single field error
     * - Expected: Returns 400 with map containing field errors
     * - Coverage Target: handleValidationException method, lines 25-33
     */
    @Test
    @DisplayName("Should return 400 with field errors for validation exception")
    void testHandleValidationException() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("book", "title", "must not be blank");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Map<String, Object>> response = handler.handleValidationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().get("statusCode"));
        assertEquals("Validation failed", response.getBody().get("message"));
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assertEquals("must not be blank", errors.get("title"));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleValidationException
     * - Scenario: MethodArgumentNotValidException with multiple field errors
     * - Expected: Returns 400 with all field errors included in response
     * - Coverage Target: handleValidationException lambda iteration, lines 27-29
     */
    @Test
    @DisplayName("Should return 400 with multiple field errors")
    void testHandleValidationExceptionMultipleFields() {
        BindingResult bindingResult = mock(BindingResult.class);
        List<FieldError> fieldErrors = List.of(
                new FieldError("book", "title", "must not be blank"),
                new FieldError("book", "author", "must not be null")
        );
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        ResponseEntity<Map<String, Object>> response = handler.handleValidationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assertEquals(2, errors.size());
        assertEquals("must not be blank", errors.get("title"));
        assertEquals("must not be null", errors.get("author"));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleUserAlreadyExists
     * - Scenario: UserAlreadyExistsException is thrown
     * - Expected: Returns 409 with ErrorResponse containing exception message
     * - Coverage Target: handleUserAlreadyExists method, lines 36-37
     */
    @Test
    @DisplayName("Should return 409 with error message for UserAlreadyExistsException")
    void testHandleUserAlreadyExists() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("john");

        ResponseEntity<ErrorResponse> response = handler.handleUserAlreadyExists(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User already exists with username: john", response.getBody().message());
        assertEquals(409, response.getBody().statusCode());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: handleGenericException
     * - Scenario: Generic Exception is thrown
     * - Expected: Returns 500 with "Internal Server Error" message
     * - Coverage Target: handleGenericException method, lines 40-42
     */
    @Test
    @DisplayName("Should return 500 with Internal Server Error for generic exception")
    void testHandleGenericException() {
        Exception ex = new RuntimeException("Something went wrong");

        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody().message());
        assertEquals(500, response.getBody().statusCode());
    }
}
