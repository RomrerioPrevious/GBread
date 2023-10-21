package com.gbread.executors.tokens;

public enum TokenTypeList {
    IF(new TokenType("IF", "if")),
    ELSE(new TokenType("ELSE", "else")),
    WHILE(new TokenType("WHILE", "while")),
    TRUE(new TokenType("TRUE", "true")),
    FALSE(new TokenType("FALSE", "false")),
    FUNCTION(new TokenType("FUNCTION", "func")),
    NUMBER(new TokenType("NUMBER", "\\d+")),
    EQUALITY(new TokenType("EQUALITY", "==")),
    NOT_EQUALITY(new TokenType("NOT_EQUALITY", "!=")),
    ASSIGN(new TokenType("ASSIGN", "=")),
    SPACE(new TokenType("SPACE", " ")),
    VARIABLE(new TokenType("VARIABLE", "[a-zA-Z]+")),
    PLUS(new TokenType("PLUS", "\\+")),
    MINUS(new TokenType("MINUS", "\\-")),
    LEFT_PAR(new TokenType("LEFT_PAR", "\\(")),
    RIGHT_PAR(new TokenType("RIGHT_PAR", "\\)")),
    RIGHT_CUR(new TokenType("RIGHT_CUR", "\\}")),
    LEFT_CUR(new TokenType("LEFT_CUR", "\\{")),
    SEMICOLON(new TokenType("SEMICOLON", ";"));

    public TokenType tokenType;

    TokenTypeList(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
