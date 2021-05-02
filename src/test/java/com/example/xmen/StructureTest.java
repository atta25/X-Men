package com.example.xmen;

import com.example.xmen.dto.DnaRequestBody;
import com.example.xmen.exception.InvalidCharacterException;
import com.example.xmen.exception.StructureLengthException;
import org.junit.Test;

public class StructureTest {
    @Test
    public void whenTheCharactersAreValidShouldNotReturnAnException() {
        String[] dna = {"TCCCGA","CAGTGC","TTCTGT","AGAAGG","CCCCTA","TCACTG"};
        DnaRequestBody dnaRequestBody = new DnaRequestBody();
        dnaRequestBody.setDna(dna);
    }

    @Test(expected = StructureLengthException.class)
    public void whenTheSizeIsNotUniformShouldReturnAnException() {
        String[] dna = {"ATCCCC","CAG","TTATGT","AGAAGG","CCCTTA","TCACTG"};
        DnaRequestBody dnaRequestBody = new DnaRequestBody();
        dnaRequestBody.setDna(dna);
    }

    @Test(expected = InvalidCharacterException.class)
    public void whenTheCharactersAreNotValidShouldReturnAnException() {
        String[] dna = {"ATCXXX","CAGAAA","TTATGT","AGAAGG","CCCTTA","TCACTG"};
        DnaRequestBody dnaRequestBody = new DnaRequestBody();
        dnaRequestBody.setDna(dna);
    }
}
