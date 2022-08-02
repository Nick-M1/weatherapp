package com.nick.weatherapp.exceptions;

public class LocationNotFound extends GeneralException {

    public LocationNotFound(String message) {
        super(message);
    }

    public LocationNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationNotFound(Throwable cause) {
        super(cause);
    }

    public LocationNotFound(){}
}
