package com.example.xmen.exception;

public class InvalidCharacterException extends RuntimeException {
    public InvalidCharacterException() {
        super("Invalid characters in dna structure");
    }
}
