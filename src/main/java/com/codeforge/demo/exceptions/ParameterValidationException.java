package com.codeforge.demo.exceptions;

public class ParameterValidationException extends RuntimeException {

    public ParameterValidationException(String queryParam) {
        super("Query parameter contains characters that are not allowed: " + queryParam);
    }
}
