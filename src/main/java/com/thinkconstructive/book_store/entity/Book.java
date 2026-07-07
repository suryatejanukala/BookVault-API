package com.thinkconstructive.book_store.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Schema(description = "Book entity")
public record Book(@Id String bookId, String name, String price, String author, String description) {

}
