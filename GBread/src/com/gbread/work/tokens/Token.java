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
        return isOperator(new TokenType[] {
                TokenTypeList.PLUS.tokenType,
                TokenTypeList.MINUS.tokenType
        });
    }

    public boolean isUnaryOperator(){
        return isOperator(new TokenType[] {
                TokenTypeList.IF.tokenType,
                TokenTypeList.ELSE.tokenType,
                TokenTypeList.WHILE.tokenType
        });
    }

    private boolean isOperator(TokenType[] types){
        for (TokenType type: types) {
            if (this.type == type){
                return true;
            }
        }
        return false;
    }
}
