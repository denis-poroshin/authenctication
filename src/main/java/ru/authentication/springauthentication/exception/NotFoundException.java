package ru.authentication.springauthentication.exception;

public class NotFoundException extends RuntimeException {
    private long id;

    public NotFoundException(long id) {
        this.id = id;
    }
    public long getId(){
        return id;
    }
}
