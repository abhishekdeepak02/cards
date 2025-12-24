package com.lazycoder.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Cards",
        description = "Schema to hold card information associated with a customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {

    @Schema(
            description = "Mobile Number of the Customer",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Card Number of the Customer",
            example = "1234-5678-9012-3456"
    )
    @Pattern(regexp = "(^$|[0-9]{16})",
            message = "Card number must be 16 digit."
    )
    private String cardNumber;
    @Schema(
            description = "Type of the Card",
            example = "Credit Card"
    )
    @NotEmpty(message = "Card type cannot be empty.")
    private String cardType;

    @Schema(
            description = "Total Limit of the Card",
            example = "100000.00"
    )
    @Max(value = 1000000, message = "Total limit should not exceed 50000.")
    private Long totalLimit;

    @Schema(
            description = "Amount Used from the Card",
            example = "25000.00"
    )
    @Positive(message = "Amount used should be greater than 0.")
    private Long amountUsed;
    @Schema(
            description = "Available Amount on the Card",
            example = "75000.00"
    )
    @PositiveOrZero(message = "Available amount should be 0 or greater than 0.")
    private Long availableAmount;
}
