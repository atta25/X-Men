package com.example.xmen.exception;

public class StructureLengthException extends RuntimeException {
    public StructureLengthException() {
        super("Invalid length of dna structure");
    }
}
