package com.bvc.sodv3203_finalproject.workouts;


/**
 * I don't want to talk about this class
 * This class should not exist
 * But it is a custom exception that needs to be sent
 * In order for us to read from the file successfully upon boot.
 */
public class DataFileNotFoundException extends RuntimeException {
    public DataFileNotFoundException(String message) {
        super(message);
    }
}
