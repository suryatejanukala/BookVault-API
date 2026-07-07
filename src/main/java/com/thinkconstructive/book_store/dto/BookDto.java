package com.thinkconstructive.book_store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Book Dto information")
public record BookDto(
        String bookId,

        @NotBlank(message = "Book name is required")
        @Size(min = 2, max = 100, message = "Book name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Price is required")
        String price,

        @NotBlank(message = "Author is required")
        @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 characters")
        String author,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
) {
}
