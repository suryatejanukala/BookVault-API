package com.thinkconstructive.book_store.service.impl;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;
import com.thinkconstructive.book_store.exception.BookNotFoundException;
import com.thinkconstructive.book_store.mapper.BookMapper;
import com.thinkconstructive.book_store.repository.BookRepository;
import com.thinkconstructive.book_store.service.BookService;
import com.thinkconstructive.book_store.util.ResponseStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseStructure getBook(String bookId) {
        log.debug("Fetching book with id: {}", bookId);
        Book book = bookRepository.findBookByBookId(bookId);
        if (book == null) {
            throw new BookNotFoundException(bookId);
        }
        return ResponseStructure.builder()
                .status(HttpStatus.OK.value())
                .message("Book fetched successfully")
                .data(BookMapper.toDto(book))
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public ResponseStructure getAllBooks() {
        log.debug("Fetching all books");
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtoList = books.stream().map(BookMapper::toDto).toList();
        return ResponseStructure.builder()
                .status(HttpStatus.OK.value())
                .message("All books fetched successfully")
                .data(bookDtoList)
                .dateTime(LocalDateTime.now())
                .totalRecords(bookDtoList.size())
                .build();
    }

    @Override
    public ResponseStructure createBook(BookDto bookDto) {
        Book book = bookRepository.insert(BookMapper.toEntity(bookDto));
        log.info("Book created with id: {}", book.bookId());
        return ResponseStructure.builder()
                .status(HttpStatus.CREATED.value())
                .message("Book created successfully")
                .data(BookMapper.toDto(book))
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public ResponseStructure updateBookName(BookDto bookDto) {
        log.debug("Updating book: {}", bookDto.bookId());
        Book book = bookRepository.findBookByBookId(bookDto.bookId());
        if (book == null) {
            throw new BookNotFoundException(bookDto.bookId());
        }
        bookRepository.updateBookNameByBookId(bookDto.bookId(), bookDto.name());
        log.info("Book updated: {}", bookDto.bookId());
        return ResponseStructure.builder()
                .status(HttpStatus.OK.value())
                .message("Book updated successfully")
                .data(BookMapper.toDto(bookRepository.findBookByBookId(bookDto.bookId())))
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Override
    public ResponseStructure deleteBookByBookId(String bookId) {
        Book book = bookRepository.findBookByBookId(bookId);
        if (book == null) {
            throw new BookNotFoundException(bookId);
        }
        bookRepository.deleteBookByBookId(bookId);
        log.info("Book deleted: {}", bookId);
        return ResponseStructure.builder()
                .status(HttpStatus.OK.value())
                .message("Book deleted successfully")
                .dateTime(LocalDateTime.now())
                .build();
    }
}
