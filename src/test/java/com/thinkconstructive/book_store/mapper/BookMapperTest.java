package com.thinkconstructive.book_store.mapper;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BookMapper Tests")
class BookMapperTest {

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: Book with all fields maps correctly to BookDto
     * - Expected: BookDto has same field values as Book
     * - Coverage Target: toDto method happy path
     */
    @Test
    @DisplayName("Should map Book to BookDto with all fields")
    void testToDtoSuccess() {
        Book book = new Book("1", "Java Basics", "29.99", "Author1", "A Java book");

        BookDto result = BookMapper.toDto(book);

        assertEquals("1", result.bookId());
        assertEquals("Java Basics", result.name());
        assertEquals("29.99", result.price());
        assertEquals("Author1", result.author());
        assertEquals("A Java book", result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: BookDto with all fields maps correctly to Book
     * - Expected: Book has same field values as BookDto
     * - Coverage Target: toEntity method happy path
     */
    @Test
    @DisplayName("Should map BookDto to Book with all fields")
    void testToEntitySuccess() {
        BookDto bookDto = new BookDto("2", "Spring Boot", "39.99", "Author2", "A Spring book");

        Book result = BookMapper.toEntity(bookDto);

        assertEquals("2", result.bookId());
        assertEquals("Spring Boot", result.name());
        assertEquals("39.99", result.price());
        assertEquals("Author2", result.author());
        assertEquals("A Spring book", result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: Book with null fields maps to BookDto with null fields
     * - Expected: BookDto fields are null
     * - Coverage Target: toDto null field handling
     */
    @Test
    @DisplayName("Should map Book with null fields to BookDto with null fields")
    void testToDtoWithNullFields() {
        Book book = new Book(null, null, null, null, null);

        BookDto result = BookMapper.toDto(book);

        assertNull(result.bookId());
        assertNull(result.name());
        assertNull(result.price());
        assertNull(result.author());
        assertNull(result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: BookDto with null fields maps to Book with null fields
     * - Expected: Book fields are null
     * - Coverage Target: toEntity null field handling
     */
    @Test
    @DisplayName("Should map BookDto with null fields to Book with null fields")
    void testToEntityWithNullFields() {
        BookDto bookDto = new BookDto(null, null, null, null, null);

        Book result = BookMapper.toEntity(bookDto);

        assertNull(result.bookId());
        assertNull(result.name());
        assertNull(result.price());
        assertNull(result.author());
        assertNull(result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: Book with empty string fields maps correctly
     * - Expected: BookDto has empty string fields
     * - Coverage Target: toDto empty string handling
     */
    @Test
    @DisplayName("Should map Book with empty strings to BookDto with empty strings")
    void testToDtoWithEmptyStrings() {
        Book book = new Book("", "", "", "", "");

        BookDto result = BookMapper.toDto(book);

        assertEquals("", result.bookId());
        assertEquals("", result.name());
        assertEquals("", result.price());
        assertEquals("", result.author());
        assertEquals("", result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: BookDto with empty string fields maps correctly
     * - Expected: Book has empty string fields
     * - Coverage Target: toEntity empty string handling
     */
    @Test
    @DisplayName("Should map BookDto with empty strings to Book with empty strings")
    void testToEntityWithEmptyStrings() {
        BookDto bookDto = new BookDto("", "", "", "", "");

        Book result = BookMapper.toEntity(bookDto);

        assertEquals("", result.bookId());
        assertEquals("", result.name());
        assertEquals("", result.price());
        assertEquals("", result.author());
        assertEquals("", result.description());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toDto
     * - Scenario: Passing null Book throws NullPointerException
     * - Expected: NullPointerException thrown
     * - Coverage Target: toDto null input error path
     */
    @Test
    @DisplayName("Should throw NullPointerException when Book is null")
    void testToDtoWithNullBook() {
        assertThrows(NullPointerException.class, () -> BookMapper.toDto(null));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: toEntity
     * - Scenario: Passing null BookDto throws NullPointerException
     * - Expected: NullPointerException thrown
     * - Coverage Target: toEntity null input error path
     */
    @Test
    @DisplayName("Should throw NullPointerException when BookDto is null")
    void testToEntityWithNullBookDto() {
        assertThrows(NullPointerException.class, () -> BookMapper.toEntity(null));
    }
}
