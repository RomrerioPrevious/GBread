package com.gbread.executors.tokens;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == TokenType.class){
            return Objects.equals(((TokenType) obj).name, name);
        }
        return false;
    }
}
