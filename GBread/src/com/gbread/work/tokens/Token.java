package com.gbread.work.tokens;

public class Token {
    private final TokenType type;
    private final String text;
    private final int position;

    public Token(TokenType type, String text, int position) {
        this.type = type;
        this.text = text;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", position=" + position +
                '}';
    }

    public TokenType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getPosition() {
        return position;
    }
}
