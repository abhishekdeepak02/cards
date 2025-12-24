package com.lazycoder.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold standard response information")
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "Status of the Response"
    )
    private String status;
    @Schema(
            description = "Message of the Response"
    )
    private String message;
}
