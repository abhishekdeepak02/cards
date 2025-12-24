package com.lazycoder.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "CardTxn",
        description = "Schema to hold card transaction information")
@Data
@AllArgsConstructor
public class CardTxnDto {

    @Schema(
            description = "Mobile Number of the Customer",
            example = "9876543210"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;
    @Schema(
            description = "Card Number of the Customer",
            example = "1234-5678-9012-3456"
    )
    @PositiveOrZero(message = "Amount debited should be 0 or greater than 0.")
    private Long amountDebited;
}
