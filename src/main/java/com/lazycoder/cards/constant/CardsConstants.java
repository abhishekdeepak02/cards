package com.lazycoder.cards.constant;

public final class CardsConstants {



    private CardsConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String CREDIT_CARD = "CREDIT_CARD";
    public static final String DEBIT_CARD = "DEBIT_CARD";

    public static final int NEW_CARD_LIMIT = 50_000;

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Card created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact Dev team";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "Internal server error. Please contact Dev team";

}
