package com.gbread.exceptions;

import com.gbread.exceptions.tokenExceptions.TokenException;

public class SyntaxException extends TokenException {
    String uncorrectedWord;
    int position;

    public SyntaxException(String uncorrectedWord, int position) {
        this.position = position;
        this.uncorrectedWord = uncorrectedWord;
    }

    @Override
    public String getMessage() {
        return "Syntax exception " + uncorrectedWord + " on position " + position;
    }

    public SyntaxException() {
    }
}
