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
        return isOperator(
                TokenTypeList.PLUS.tokenType,
                TokenTypeList.MINUS.tokenType
        );
    }

    public boolean isUnaryOperator(){
        return isOperator(
                TokenTypeList.IF.tokenType,
                TokenTypeList.ELSE.tokenType,
                TokenTypeList.WHILE.tokenType
        );
    }

    private boolean isOperator(TokenType... types){
        for (TokenType type: types) {
            if (this.type == type){
                return true;
            }
        }
        return false;
    }
}
