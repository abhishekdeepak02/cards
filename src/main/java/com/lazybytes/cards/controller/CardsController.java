package com.lazybytes.cards.controller;

import com.lazybytes.cards.constant.CardsConstants;
import com.lazybytes.cards.dto.CardTxnDto;
import com.lazybytes.cards.dto.CardsDto;
import com.lazybytes.cards.dto.ResponseDto;
import com.lazybytes.cards.entity.Cards;
import com.lazybytes.cards.mapper.CardsMapper;
import com.lazybytes.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Cards API",
        description = "API to manage card operations such as create, fetch, debit, and delete card accounts"
)
@RestController
@RequestMapping(path = "/api/cards", produces = "application/json")
@AllArgsConstructor
public class CardsController {

    private ICardService iCardService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create a card account for a customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.") String mobileNumber) {
        // Implementation goes here
        iCardService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details for a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found for the given mobile number"
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> getCardDetails(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")String mobileNumber) {
        // Implementation goes here
        Cards cardDetails = iCardService.fetchCardDetails(mobileNumber);
        CardsDto cardDto = CardsMapper.mapToCardDto(cardDetails, new CardsDto());
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardDto);
    }

    @Operation(
            summary = "Debit Card Amount REST API",
            description = "REST API to debit amount from a customer's card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found for the given mobile number or insufficient balance"
            )
    }
    )
    @PutMapping("/debit")
    public ResponseEntity<CardsDto> debitCardAmount(@RequestBody @Valid CardTxnDto cardTxnDto) {
        // Implementation goes here
        CardsDto updatedCardDto = iCardService.debitCardAmount(cardTxnDto.getMobileNumber(), cardTxnDto.getAmountDebited());
        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedCardDto);
    }

    @Operation(
            summary = "Delete Card Account REST API",
            description = "REST API to delete a customer's card account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status 200 OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardAccount(@RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")String mobileNumber) {
        // Implementation goes here
        boolean isDeleted = iCardService.deleteCardAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(CardsConstants.STATUS_500, CardsConstants.MESSAGE_500));
        }
    }
}
