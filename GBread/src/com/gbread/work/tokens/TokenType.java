package com.gbread.work.tokens;

public class TokenType {
    final String name;

    public final String regex;

    public TokenType(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "TokenType{" +
                "name='" + name + '\'' +
                '}';
    }
}
