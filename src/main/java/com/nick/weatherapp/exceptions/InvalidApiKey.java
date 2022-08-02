package com.nick.weatherapp.exceptions;

public class InvalidApiKey extends GeneralException {
    public InvalidApiKey(String message) {
        super(message);
    }

    public InvalidApiKey(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidApiKey(Throwable cause) {
        super(cause);
    }

    public InvalidApiKey(){}
}
