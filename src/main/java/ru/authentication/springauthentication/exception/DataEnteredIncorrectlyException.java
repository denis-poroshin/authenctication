package ru.authentication.springauthentication.exception;

public class DataEnteredIncorrectlyException extends RuntimeException{
    public DataEnteredIncorrectlyException(String message) {
        super(message);
    }
}
