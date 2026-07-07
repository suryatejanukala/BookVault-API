package com.thinkconstructive.book_store.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response Structure for all APIs")
public class ResponseStructure {
    @Schema(description = "Response Status Code")
    private int status;
    @Schema(description = "Response message")
    private String message;
    @Schema(description = "Response Data")
    private Object data;
    @Schema(description = "Response Date and Time")
    private LocalDateTime dateTime;
    @Schema(description = "Response Total Records")
    private Integer totalRecords;
}
