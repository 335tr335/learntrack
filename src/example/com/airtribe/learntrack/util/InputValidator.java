package example.com.airtribe.learntrack.util;

import example.com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    private InputValidator() {}

    public static void validateString(String input, String fieldName) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be null or empty.");
        }
    }
}