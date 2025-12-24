package com.lazycoder.cards.service.impl;

import com.lazycoder.cards.constant.CardsConstants;
import com.lazycoder.cards.dto.CardsDto;
import com.lazycoder.cards.entity.Cards;
import com.lazycoder.cards.exception.ResourceNotFoundException;
import com.lazycoder.cards.mapper.CardsMapper;
import com.lazycoder.cards.repository.CardsJpaRepository;
import com.lazycoder.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService implements ICardService {

    private CardsJpaRepository cardsJpaRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsJpaRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()) {
            throw new ResourceNotFoundException("Card already exist for mobile number: " + mobileNumber);
        }

        cardsJpaRepository.save(createCardAccount(mobileNumber));


}

    @Override
    public Cards fetchCardDetails(String mobileNumber) {
        Cards card = cardsJpaRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Card not found for mobile number: " + mobileNumber));
        return card;
    }

    @Override
    public CardsDto debitCardAmount(String mobileNumber, Long amount) {
        Cards card = cardsJpaRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
        new ResourceNotFoundException("Card not found for mobile number: " + mobileNumber));

        withdrawAmount(card, amount);
        Cards savedCard = cardsJpaRepository.save(card);
        return CardsMapper.mapToCardDto(savedCard, new CardsDto());
    }

    @Override
    public boolean deleteCardAccount(String mobileNumber) {
        boolean isDeleted = false;
        Cards card = cardsJpaRepository.findByMobileNumber(mobileNumber).orElseThrow(() ->
                new ResourceNotFoundException("Card not found for mobile number: " + mobileNumber));

        cardsJpaRepository.deleteById(card.getCardId());
        isDeleted = true;
        return isDeleted;

    }

    private void withdrawAmount(Cards card, Long amount) {
        Long availableAmount = card.getAvailableAmount();
        if(availableAmount < amount) {
            throw new ResourceNotFoundException("Insufficient balance in card for mobile number: " + card.getMobileNumber());
        }
        card.setAvailableAmount(availableAmount - amount);
        card.setAmountUsed(card.getAmountUsed() + amount);
    }

    private Cards createCardAccount(String mobileNumber) {
        Cards cards = new Cards();
        cards.setMobileNumber(mobileNumber);
        cards.setCardNumber(generateCardNumber());
        cards.setCardType(CardsConstants.CREDIT_CARD);
        cards.setAmountUsed(0L);
        cards.setAvailableAmount((long) CardsConstants.NEW_CARD_LIMIT);
        cards.setTotalLimit((long) CardsConstants.NEW_CARD_LIMIT);
        return cards;
    }

    private String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
}
