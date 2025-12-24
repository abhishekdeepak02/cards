package com.lazycoder.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold standard error response information")
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API Path where the error occurred",
            example = "/api/cards/"
    )
    private String apiPath;

    @Schema(
            description = "HTTP Status representing the error"
    )
    private HttpStatus errorStatus;
    @Schema(
            description = "Error Message describing the error"
    )
    private String errorMessage;
    @Schema(
            description = "Timestamp when the error occurred",
            example = "2024-06-15T14:30:00"
    )
    private LocalDateTime errorTimestamp;
}
