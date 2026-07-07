package com.thinkconstructive.book_store.service;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.util.ResponseStructure;

public interface BookService {
    ResponseStructure getBook(String bookId);
    ResponseStructure getAllBooks();
    ResponseStructure createBook(BookDto bookDto);
    ResponseStructure updateBookName(BookDto bookDto);
    ResponseStructure deleteBookByBookId(String bookId);
}
