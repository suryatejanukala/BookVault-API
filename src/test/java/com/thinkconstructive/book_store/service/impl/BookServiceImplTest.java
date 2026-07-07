package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;
import com.thinkconstructive.book_store.exception.BookNotFoundException;
import com.thinkconstructive.book_store.repository.BookRepository;
import com.thinkconstructive.book_store.util.ResponseStructure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookServiceImpl Tests")
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getBook
     * - Scenario: Valid bookId returns wrapped BookDto response with HTTP 200
     * - Expected: ResponseStructure with status 200, message, and BookDto data
     * - Coverage Target: getBook happy path, lines 26-34
     */
    @Test
    @DisplayName("Should return book when valid bookId is provided")
    void testGetBookSuccess() {
        Book book = new Book("B001", "Java Basics", "29.99", "Author A", "A Java book");
        when(bookRepository.findBookByBookId("B001")).thenReturn(book);

        ResponseStructure result = bookService.getBook("B001");

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Book fetched successfully", result.getMessage());
        assertNotNull(result.getData());
        assertNotNull(result.getDateTime());
        BookDto dto = (BookDto) result.getData();
        assertEquals("B001", dto.bookId());
        assertEquals("Java Basics", dto.name());
        verify(bookRepository).findBookByBookId("B001");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getAllBooks
     * - Scenario: Returns list of all books wrapped in ResponseStructure with totalRecords
     * - Expected: ResponseStructure with status 200 and list of BookDto
     * - Coverage Target: getAllBooks happy path, lines 37-45
     */
    @Test
    @DisplayName("Should return all books successfully")
    void testGetAllBooksSuccess() {
        List<Book> books = List.of(
                new Book("B001", "Java Basics", "29.99", "Author A", "Desc A"),
                new Book("B002", "Spring Boot", "39.99", "Author B", "Desc B")
        );
        when(bookRepository.findAll()).thenReturn(books);

        ResponseStructure result = bookService.getAllBooks();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("All books fetched successfully", result.getMessage());
        assertNotNull(result.getData());
        assertEquals(2, result.getTotalRecords());
        List<?> data = (List<?>) result.getData();
        assertEquals(2, data.size());
        verify(bookRepository).findAll();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createBook
     * - Scenario: Inserts book and returns HTTP 201 with created BookDto
     * - Expected: ResponseStructure with status 201 and created BookDto
     * - Coverage Target: createBook happy path, lines 48-55
     */
    @Test
    @DisplayName("Should create book and return 201 status")
    void testCreateBookSuccess() {
        BookDto inputDto = new BookDto("B003", "New Book", "19.99", "Author C", "New desc");
        Book savedBook = new Book("B003", "New Book", "19.99", "Author C", "New desc");
        when(bookRepository.insert(any(Book.class))).thenReturn(savedBook);

        ResponseStructure result = bookService.createBook(inputDto);

        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
        assertEquals("Book created successfully", result.getMessage());
        assertNotNull(result.getData());
        BookDto dto = (BookDto) result.getData();
        assertEquals("B003", dto.bookId());
        verify(bookRepository).insert(any(Book.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: updateBookName
     * - Scenario: Finds book, updates name, returns updated BookDto
     * - Expected: ResponseStructure with status 200 and updated BookDto
     * - Coverage Target: updateBookName happy path, lines 58-68
     */
    @Test
    @DisplayName("Should update book name successfully")
    void testUpdateBookNameSuccess() {
        BookDto inputDto = new BookDto("B001", "Updated Name", "29.99", "Author A", "Desc");
        Book existingBook = new Book("B001", "Old Name", "29.99", "Author A", "Desc");
        Book updatedBook = new Book("B001", "Updated Name", "29.99", "Author A", "Desc");

        when(bookRepository.findBookByBookId("B001"))
                .thenReturn(existingBook)
                .thenReturn(updatedBook);

        ResponseStructure result = bookService.updateBookName(inputDto);

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Book updated successfully", result.getMessage());
        BookDto dto = (BookDto) result.getData();
        assertEquals("Updated Name", dto.name());
        verify(bookRepository).updateBookNameByBookId("B001", "Updated Name");
        verify(bookRepository, times(2)).findBookByBookId("B001");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: deleteBookByBookId
     * - Scenario: Finds book, deletes it, returns HTTP 200
     * - Expected: ResponseStructure with status 200 and success message
     * - Coverage Target: deleteBookByBookId happy path, lines 71-81
     */
    @Test
    @DisplayName("Should delete book successfully")
    void testDeleteBookByBookIdSuccess() {
        Book book = new Book("B001", "Java Basics", "29.99", "Author A", "Desc");
        when(bookRepository.findBookByBookId("B001")).thenReturn(book);

        ResponseStructure result = bookService.deleteBookByBookId("B001");

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals("Book deleted successfully", result.getMessage());
        assertNull(result.getData());
        verify(bookRepository).deleteBookByBookId("B001");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getAllBooks
     * - Scenario: No books in repository, returns empty list with totalRecords=0
     * - Expected: ResponseStructure with empty list and totalRecords 0
     * - Coverage Target: getAllBooks edge case, empty list branch
     */
    @Test
    @DisplayName("Should return empty list when no books exist")
    void testGetAllBooksEmptyList() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseStructure result = bookService.getAllBooks();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(0, result.getTotalRecords());
        List<?> data = (List<?>) result.getData();
        assertTrue(data.isEmpty());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createBook
     * - Scenario: BookDto with null fields (depends on validation)
     * - Expected: Repository insert is called regardless (no service-level validation)
     * - Coverage Target: createBook with null field handling
     */
    @Test
    @DisplayName("Should create book even with null optional fields")
    void testCreateBookWithNullFields() {
        BookDto inputDto = new BookDto("B004", "Book", "9.99", "Author", null);
        Book savedBook = new Book("B004", "Book", "9.99", "Author", null);
        when(bookRepository.insert(any(Book.class))).thenReturn(savedBook);

        ResponseStructure result = bookService.createBook(inputDto);

        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
        assertNotNull(result.getData());
        verify(bookRepository).insert(any(Book.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getBook
     * - Scenario: bookId not in repository, throws BookNotFoundException
     * - Expected: BookNotFoundException thrown with appropriate message
     * - Coverage Target: getBook error path, null check branch at line 28
     */
    @Test
    @DisplayName("Should throw BookNotFoundException when book not found")
    void testGetBookNotFound() {
        when(bookRepository.findBookByBookId("INVALID")).thenReturn(null);

        BookNotFoundException ex = assertThrows(BookNotFoundException.class,
                () -> bookService.getBook("INVALID"));

        assertTrue(ex.getMessage().contains("INVALID"));
        verify(bookRepository).findBookByBookId("INVALID");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: updateBookName
     * - Scenario: bookDto.bookId() not found, throws BookNotFoundException
     * - Expected: BookNotFoundException thrown
     * - Coverage Target: updateBookName error path, null check branch at line 60
     */
    @Test
    @DisplayName("Should throw BookNotFoundException when updating non-existent book")
    void testUpdateBookNameNotFound() {
        BookDto inputDto = new BookDto("INVALID", "Name", "10.00", "Author", "Desc");
        when(bookRepository.findBookByBookId("INVALID")).thenReturn(null);

        assertThrows(BookNotFoundException.class,
                () -> bookService.updateBookName(inputDto));

        verify(bookRepository).findBookByBookId("INVALID");
        verify(bookRepository, never()).updateBookNameByBookId(any(), any());
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: deleteBookByBookId
     * - Scenario: bookId not found, throws BookNotFoundException
     * - Expected: BookNotFoundException thrown
     * - Coverage Target: deleteBookByBookId error path, null check branch at line 73
     */
    @Test
    @DisplayName("Should throw BookNotFoundException when deleting non-existent book")
    void testDeleteBookByBookIdNotFound() {
        when(bookRepository.findBookByBookId("INVALID")).thenReturn(null);

        assertThrows(BookNotFoundException.class,
                () -> bookService.deleteBookByBookId("INVALID"));

        verify(bookRepository).findBookByBookId("INVALID");
        verify(bookRepository, never()).deleteBookByBookId(any());
    }
}
