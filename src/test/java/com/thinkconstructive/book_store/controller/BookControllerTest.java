package com.thinkconstructive.book_store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.exception.BookNotFoundException;
import com.thinkconstructive.book_store.exception.GlobalExceptionHandler;
import com.thinkconstructive.book_store.service.BookService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookController Tests")
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    private ResponseStructure buildResponse(int status, String message, Object data) {
        return ResponseStructure.builder()
                .status(status)
                .message(message)
                .data(data)
                .dateTime(LocalDateTime.now())
                .build();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: welcome()
     * - Scenario: GET /book-store/welcome returns welcome message
     * - Expected: 200 OK with "Welcome to Book Store"
     * - Coverage Target: welcome() method, lines covering ResponseEntity creation
     */
    @Test
    @DisplayName("Should return welcome message with 200")
    void testWelcome() throws Exception {
        mockMvc.perform(get("/book-store/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Book Store"));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getBook(String)
     * - Scenario: GET /book-store/{bookId} delegates to service and returns 200
     * - Expected: 200 OK with ResponseStructure from service
     * - Coverage Target: getBook() method happy path
     */
    @Test
    @DisplayName("Should get book by id with 200")
    void testGetBook() throws Exception {
        ResponseStructure response = buildResponse(200, "Book found", new BookDto("B001", "Java", "500", "Author", "Desc"));
        when(bookService.getBook("B001")).thenReturn(response);

        mockMvc.perform(get("/book-store/B001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book found"));

        verify(bookService).getBook("B001");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getAllBooks()
     * - Scenario: GET /book-store/ delegates to service and returns 200
     * - Expected: 200 OK with ResponseStructure containing list
     * - Coverage Target: getAllBooks() method happy path
     */
    @Test
    @DisplayName("Should get all books with 200")
    void testGetAllBooks() throws Exception {
        ResponseStructure response = buildResponse(200, "All books", List.of());
        when(bookService.getAllBooks()).thenReturn(response);

        mockMvc.perform(get("/book-store/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All books"));

        verify(bookService).getAllBooks();
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createBooks(BookDto)
     * - Scenario: POST /book-store/ with valid BookDto returns 201
     * - Expected: 201 Created with ResponseStructure from service
     * - Coverage Target: createBooks() method happy path
     */
    @Test
    @DisplayName("Should create book with 201")
    void testCreateBooks() throws Exception {
        BookDto bookDto = new BookDto("B001", "Java Programming", "500", "Author Name", "Description");
        ResponseStructure response = buildResponse(201, "Book created", bookDto);
        when(bookService.createBook(any(BookDto.class))).thenReturn(response);

        mockMvc.perform(post("/book-store/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Book created"));

        verify(bookService).createBook(any(BookDto.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: updateBook(BookDto)
     * - Scenario: PUT /book-store/ with valid BookDto returns 200
     * - Expected: 200 OK with ResponseStructure from service
     * - Coverage Target: updateBook() method happy path
     */
    @Test
    @DisplayName("Should update book with 200")
    void testUpdateBook() throws Exception {
        BookDto bookDto = new BookDto("B001", "Updated Name", "600", "Author Name", "Updated Desc");
        ResponseStructure response = buildResponse(200, "Book updated", bookDto);
        when(bookService.updateBookName(any(BookDto.class))).thenReturn(response);

        mockMvc.perform(put("/book-store/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book updated"));

        verify(bookService).updateBookName(any(BookDto.class));
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: deleteBook(String)
     * - Scenario: DELETE /book-store/{bookId} delegates to service and returns 200
     * - Expected: 200 OK with ResponseStructure from service
     * - Coverage Target: deleteBook() method happy path
     */
    @Test
    @DisplayName("Should delete book with 200")
    void testDeleteBook() throws Exception {
        ResponseStructure response = buildResponse(200, "Book deleted", null);
        when(bookService.deleteBookByBookId("B001")).thenReturn(response);

        mockMvc.perform(delete("/book-store/B001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Book deleted"));

        verify(bookService).deleteBookByBookId("B001");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: createBooks(BookDto)
     * - Scenario: POST /book-store/ with invalid BookDto (blank name) returns 400
     * - Expected: 400 Bad Request with validation errors
     * - Coverage Target: @Valid annotation validation path on createBooks
     */
    @Test
    @DisplayName("Should return 400 for invalid create request")
    void testCreateBooksInvalidDto() throws Exception {
        BookDto invalidDto = new BookDto("B001", "", "500", "", null);

        mockMvc.perform(post("/book-store/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400));

        verifyNoInteractions(bookService);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: updateBook(BookDto)
     * - Scenario: PUT /book-store/ with invalid BookDto (blank name) returns 400
     * - Expected: 400 Bad Request with validation errors
     * - Coverage Target: @Valid annotation validation path on updateBook
     */
    @Test
    @DisplayName("Should return 400 for invalid update request")
    void testUpdateBookInvalidDto() throws Exception {
        BookDto invalidDto = new BookDto("B001", "", "500", "", null);

        mockMvc.perform(put("/book-store/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400));

        verifyNoInteractions(bookService);
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: getBook(String)
     * - Scenario: GET /book-store/{bookId} when book not found
     * - Expected: 404 Not Found via GlobalExceptionHandler
     * - Coverage Target: Exception handling path for getBook
     */
    @Test
    @DisplayName("Should return 404 when book not found on get")
    void testGetBookNotFound() throws Exception {
        when(bookService.getBook("INVALID")).thenThrow(new BookNotFoundException("INVALID"));

        mockMvc.perform(get("/book-store/INVALID"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book not found with id: INVALID"));

        verify(bookService).getBook("INVALID");
    }

    /**
     * AI-GENERATED TEST CASE
     *
     * Test Plan Details:
     * - Method: deleteBook(String)
     * - Scenario: DELETE /book-store/{bookId} when book not found
     * - Expected: 404 Not Found via GlobalExceptionHandler
     * - Coverage Target: Exception handling path for deleteBook
     */
    @Test
    @DisplayName("Should return 404 when book not found on delete")
    void testDeleteBookNotFound() throws Exception {
        when(bookService.deleteBookByBookId("INVALID")).thenThrow(new BookNotFoundException("INVALID"));

        mockMvc.perform(delete("/book-store/INVALID"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Book not found with id: INVALID"));

        verify(bookService).deleteBookByBookId("INVALID");
    }
}
