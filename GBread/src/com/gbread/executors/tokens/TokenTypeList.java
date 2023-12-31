package com.gbread.executors.tokens;

public enum TokenTypeList {
    IF(new TokenType("IF", "if")),
    ELSE(new TokenType("ELSE", "else")),
    WHILE(new TokenType("WHILE", "while")),
    TRUE(new TokenType("TRUE", "true")),
    FALSE(new TokenType("FALSE", "false")),
    FUNCTION(new TokenType("FUNCTION", "function")),
    NUMBER(new TokenType("NUMBER", "\\d")),
    ASSIGN(new TokenType("ASSIGN", "=")),
    SPACE(new TokenType("SPACE", " ")),
    VARIABLE(new TokenType("VARIABLE", "[a-zA-Z]+")),
    PLUS(new TokenType("PLUS", "\\+")),
    MINUS(new TokenType("MINUS", "\\-")),
    LPAR(new TokenType("LPAR", "\\(")),
    RPAR(new TokenType("RPAR", "\\)")),
    RCUR(new TokenType("RPAR", "\\{")),
    LCUR(new TokenType("RPAR", "\\}")),
    SEMICOLON(new TokenType("SEMICOLON", ";"));

    public TokenType tokenType;

    TokenTypeList(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
