package com.thinkconstructive.book_store.controller;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.service.BookService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/book-store")
@Tag(name = "Book Store Operations")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET http://localhost:8080/book-store/welcome
    @Operation(summary = "Welcome message for Book Store API")
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Welcome to Book Store", HttpStatus.OK);
    }

    // GET http://localhost:8080/book-store/{bookId}
    @Operation(summary = "Get Book by book Id")
    @GetMapping("/{bookId}")
    public ResponseEntity<ResponseStructure> getBook(@PathVariable String bookId) {
        log.info("GET /book-store/{} - Fetching book", bookId);
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    // GET http://localhost:8080/book-store/
    @Operation(summary = "Get All Books")
    @GetMapping("/")
    public ResponseEntity<ResponseStructure> getAllBooks() {
        log.info("GET /book-store/ - Fetching all books");
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    // POST http://localhost:8080/book-store/
    @Operation(summary = "Create Book")
    @PostMapping("/")
    public ResponseEntity<ResponseStructure> createBooks(@Valid @RequestBody BookDto bookDto) {
        log.info("POST /book-store/ - Creating book: {}", bookDto.name());
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    // PUT http://localhost:8080/book-store/
    @Operation(summary = "Update Book by book Id")
    @PutMapping("/")
    public ResponseEntity<ResponseStructure> updateBook(@Valid @RequestBody BookDto bookDto) {
        log.info("PUT /book-store/ - Updating book: {}", bookDto.bookId());
        return new ResponseEntity<>(bookService.updateBookName(bookDto), HttpStatus.OK);
    }

    // DELETE http://localhost:8080/book-store/{bookId}
    @Operation(summary = "Delete Book by book Id")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<ResponseStructure> deleteBook(@PathVariable String bookId) {
        log.info("DELETE /book-store/{} - Deleting book", bookId);
        return new ResponseEntity<>(bookService.deleteBookByBookId(bookId), HttpStatus.OK);
    }
}
