package com.thinkconstructive.book_store.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String bookId) {
        super("Book not found with id: " + bookId);
    }
}
