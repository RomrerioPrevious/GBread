package com.gbread.executors.tokens;

public record Token(TokenType type, String text, int position) {

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", position=" + position +
                '}';
    }

    public boolean isBinaryOperator(){
        return isType(
                TokenTypeList.PLUS,
                TokenTypeList.MINUS,
                TokenTypeList.ASSIGN
        );
    }

    public boolean isUnaryOperator(){
        return isType(
                TokenTypeList.IF,
                TokenTypeList.ELSE,
                TokenTypeList.WHILE
        );
    }

    public boolean isFinalOperator(){
        return isType(
                TokenTypeList.SEMICOLON,
                TokenTypeList.RPAR,
                TokenTypeList.RCUR
                );
    }

    public boolean isType(TokenTypeList... types){
        for (TokenTypeList type: types) {
            if (this.type == type.tokenType){
                return true;
            }
        }
        return false;
    }
}
