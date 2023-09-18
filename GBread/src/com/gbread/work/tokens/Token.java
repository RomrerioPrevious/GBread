package com.gbread.work.tokens;

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
                TokenTypeList.PLUS.tokenType,
                TokenTypeList.MINUS.tokenType,
                TokenTypeList.ASSIGN.tokenType
        );
    }

    public boolean isUnaryOperator(){
        return isType(
                TokenTypeList.IF.tokenType,
                TokenTypeList.ELSE.tokenType,
                TokenTypeList.WHILE.tokenType
        );
    }

    public boolean isType(TokenType... types){
        for (TokenType type: types) {
            if (this.type == type){
                return true;
            }
        }
        return false;
    }
}
