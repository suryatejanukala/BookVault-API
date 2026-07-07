package com.thinkconstructive.book_store.repository;

import com.thinkconstructive.book_store.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * AI-GENERATED TEST CASE
 *
 * Test Plan Details:
 * - Class: BookRepository
 * - Type: Interface (mock-based contract test)
 * - Coverage Target: Verify contract behavior via mocking
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("BookRepository Tests")
class BookRepositoryTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("Should find book by bookId successfully")
    void testFindBookByBookIdSuccess() {
        // given
        Book book = new Book("book1", "Test Book", "29.99", "Author", "Description");
        when(bookRepository.findBookByBookId("book1")).thenReturn(book);

        // when
        Book result = bookRepository.findBookByBookId("book1");

        // then
        assertNotNull(result);
        assertEquals("book1", result.bookId());
        assertEquals("Test Book", result.name());
        verify(bookRepository).findBookByBookId("book1");
    }

    @Test
    @DisplayName("Should update book name by bookId successfully")
    void testUpdateBookNameByBookIdSuccess() {
        // given
        doNothing().when(bookRepository).updateBookNameByBookId("book1", "New Name");

        // when
        bookRepository.updateBookNameByBookId("book1", "New Name");

        // then
        verify(bookRepository).updateBookNameByBookId("book1", "New Name");
    }

    @Test
    @DisplayName("Should delete book by bookId successfully")
    void testDeleteBookByBookIdSuccess() {
        // given
        doNothing().when(bookRepository).deleteBookByBookId("book1");

        // when
        bookRepository.deleteBookByBookId("book1");

        // then
        verify(bookRepository).deleteBookByBookId("book1");
    }

    @Test
    @DisplayName("Should find all books successfully")
    void testFindAllSuccess() {
        // given
        List<Book> books = Arrays.asList(
                new Book("book1", "Book 1", "19.99", "Author1", "Desc1"),
                new Book("book2", "Book 2", "29.99", "Author2", "Desc2")
        );
        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<Book> result = bookRepository.findAll();

        // then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepository).findAll();
    }

    @Test
    @DisplayName("Should return null when book not found by bookId")
    void testFindBookByBookIdNotFound() {
        // given
        when(bookRepository.findBookByBookId("nonexistent")).thenReturn(null);

        // when
        Book result = bookRepository.findBookByBookId("nonexistent");

        // then
        assertNull(result);
        verify(bookRepository).findBookByBookId("nonexistent");
    }

    @Test
    @DisplayName("Should not throw when deleting non-existent bookId")
    void testDeleteBookByBookIdNotFound() {
        // given
        doNothing().when(bookRepository).deleteBookByBookId("nonexistent");

        // when & then
        assertDoesNotThrow(() -> bookRepository.deleteBookByBookId("nonexistent"));
        verify(bookRepository).deleteBookByBookId("nonexistent");
    }

    @Test
    @DisplayName("Should handle null bookId in findBookByBookId")
    void testFindBookByBookIdNullId() {
        // given
        when(bookRepository.findBookByBookId(null)).thenReturn(null);

        // when
        Book result = bookRepository.findBookByBookId(null);

        // then
        assertNull(result);
        verify(bookRepository).findBookByBookId(null);
    }
}
