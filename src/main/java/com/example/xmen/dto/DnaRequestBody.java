package com.example.xmen.dto;

import com.example.xmen.exception.InvalidCharacterException;
import com.example.xmen.exception.StructureLengthException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class DnaRequestBody {
    private String[] dna;
    private static final String PATTERN_OF_VALID_CHARACTERS = "[atcgATCG]+";
    private static final Logger logger = LogManager.getLogger(DnaRequestBody.class);

    public DnaRequestBody() { }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
        logger.info("Validating dna structure");
        validateSize();
        validateCharacters();
        logger.info("Structure validation completed");
    }

    private void validateSize() {
        Optional<String> first = Arrays.stream(dna).findFirst();
        if (!first.isPresent() || !Arrays.stream(dna).allMatch(s -> s.length() == first.get().length())) {
            logger.warn("The length of the structure is not valid");
            throw new StructureLengthException();
        }
    }

    private void validateCharacters() {
        Pattern pattern = Pattern.compile(PATTERN_OF_VALID_CHARACTERS);
        StringBuilder builder = new StringBuilder();
        Arrays.stream(dna).forEach(builder::append);
        if (!pattern.matcher(builder.toString()).matches()) {
            logger.warn("Dna structure has unsupported characters");
            throw new InvalidCharacterException();
        }
    }

    @Override
    public String toString() {
        return String.join(", ", Arrays.toString(dna));
    }
}
