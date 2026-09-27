package com.anattests.test1good;

// Thrown when an order is missing data needed to process it
public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String message) {
        super(message);
    }
}
