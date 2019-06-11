package com.gmail.chernii.oleksii.exceptions;

public class NotFoundUserException extends Exception {
    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String message) {
        super(message);
    }
}
