package com.gbread.executors.tokens;

public enum TokenTypeList {
    IF(new TokenType("IF", "if")),
    ELIF(new TokenType("ELIF", "elif")),
    ELSE(new TokenType("ELSE", "else")),
    WHILE(new TokenType("WHILE", "while")),
    TRUE(new TokenType("TRUE", "true")),
    FALSE(new TokenType("FALSE", "false")),
    STRING(new TokenType("STRING", "(\"[^\"]*\"|'[^']*')")),
    FUNCTION(new TokenType("FUNCTION", "func")),
    FLOAT(new TokenType("FLOAT", "\\d+\\.\\d+")),
    NUMBER(new TokenType("NUMBER", "\\d+")),
    NOT(new TokenType("NOT", "not")),
    EQUALITY(new TokenType("EQUALITY", "==")),
    NOT_EQUALITY(new TokenType("NOT_EQUALITY", "!=")),
    MORE_AND_EQUALITY(new TokenType("MORE_AND_EQUALITY", ">=")),
    LESS_AND_EQUALITY(new TokenType("LESS_AND_EQUALITY", "<=")),
    MORE(new TokenType("MORE", ">")),
    LESS(new TokenType("LESS", "<")),
    ASSIGN(new TokenType("ASSIGN", "=")),
    SPACE(new TokenType("SPACE", " ")),
    FUNCTION_USED(new TokenType("FUNCTION_USED", ".+\\(")),
    PLUS(new TokenType("PLUS", "\\+")),
    MINUS(new TokenType("MINUS", "\\-")),
    SLASH(new TokenType("SLASH", "/")),
    ASTERISK(new TokenType("ASTERISK", "\\*")),
    LEFT_PAR(new TokenType("LEFT_PAR", "\\(")),
    RIGHT_PAR(new TokenType("RIGHT_PAR", "\\)")),
    RIGHT_CUR(new TokenType("RIGHT_CUR", "\\}")),
    LEFT_CUR(new TokenType("LEFT_CUR", "\\{")),
    SEMICOLON(new TokenType("SEMICOLON", ";")),
    VARIABLE(new TokenType("VARIABLE", "[a-zA-Z]+"));

    public TokenType tokenType;

    TokenTypeList(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
