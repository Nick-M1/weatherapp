package com.nick.weatherapp.exceptions;

public class ServerSideException extends GeneralException {
    public ServerSideException(String message) {
        super(message);
    }

    public ServerSideException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerSideException(Throwable cause) {
        super(cause);
    }

    public ServerSideException(){}
}

