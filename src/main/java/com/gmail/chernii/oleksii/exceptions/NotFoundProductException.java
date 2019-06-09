package com.gmail.chernii.oleksii.exceptions;

public class NotFoundProductException extends Exception{
    public NotFoundProductException() {
    }

    public NotFoundProductException(String message) {
        super(message);
    }
}
