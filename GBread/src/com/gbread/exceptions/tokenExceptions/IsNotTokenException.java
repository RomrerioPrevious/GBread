package com.gbread.exceptions.tokenExceptions;

public class IsNotTokenException extends TokenException{
    String uncorrectedWord;
    int position;

    public IsNotTokenException(String uncorrectedWord, int position) {
        this.position = position;
        this.uncorrectedWord = uncorrectedWord;
    }

    @Override
    public String getMessage() {
        return "Uncorrected word " + uncorrectedWord + " on position " + position;
    }

    public IsNotTokenException() {
    }
}
