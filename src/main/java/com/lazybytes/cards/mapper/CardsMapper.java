package com.lazybytes.cards.mapper;

import com.lazybytes.cards.dto.CardsDto;
import com.lazybytes.cards.entity.Cards;

public class CardsMapper {

    // Maps a Cards entity to a CardsDto
    public static CardsDto mapToCardDto(Cards card, CardsDto cardsDto) {
        cardsDto.setMobileNumber(card.getMobileNumber());
        cardsDto.setCardNumber(card.getCardNumber());
        cardsDto.setCardType(card.getCardType());
        cardsDto.setTotalLimit(card.getTotalLimit());
        cardsDto.setAmountUsed(card.getAmountUsed());
        cardsDto.setAvailableAmount(card.getAvailableAmount());
        return cardsDto;
    }

    // Maps a CardsDto to a Cards entity

    public static Cards mapToCardEntity(CardsDto cardsDto, Cards card) {
        card.setMobileNumber(cardsDto.getMobileNumber());
        card.setCardNumber(cardsDto.getCardNumber());
        card.setCardType(cardsDto.getCardType());
        card.setTotalLimit(cardsDto.getTotalLimit());
        card.setAmountUsed(cardsDto.getAmountUsed());
        card.setAvailableAmount(cardsDto.getAvailableAmount());
        return card;
    }


}

