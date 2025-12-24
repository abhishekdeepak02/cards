package com.lazybytes.cards.service;

import com.lazybytes.cards.dto.CardsDto;
import com.lazybytes.cards.entity.Cards;

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
