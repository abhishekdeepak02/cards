package com.lazycoder.cards.service;

import com.lazycoder.cards.dto.CardsDto;
import com.lazycoder.cards.entity.Cards;

public interface ICardService {
    /**
     *
     * @param mobileNumber
     */
    void createCard(String mobileNumber);

    Cards fetchCardDetails(String mobileNumber);

    CardsDto debitCardAmount(String mobileNumber, Long amount);

    boolean deleteCardAccount(String mobileNumber);
}
