package com.gbread.executors.tokens;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.BooleanNode;
import com.gbread.executors.ast.objectNodes.NumberNode;
import com.gbread.executors.ast.objectNodes.VariableNode;

public record Token(TokenType type, String text, int position) {
    public Node createNodeFromToken(){
        if (this.isType(TokenTypeList.VARIABLE)) {
            return new VariableNode(this);
        }
        if (this.isType(TokenTypeList.NUMBER)) {
            return new NumberNode(this);
        }
        if (this.isType(TokenTypeList.TRUE, TokenTypeList.FALSE)){
            return new BooleanNode(this);
        }
        return null;
    }

    public boolean isBinaryOperator(){
        return isType(
                TokenTypeList.PLUS,
                TokenTypeList.MINUS,
                TokenTypeList.ASSIGN,
                TokenTypeList.EQUALITY,
                TokenTypeList.NOT_EQUALITY,
                TokenTypeList.LESS,
                TokenTypeList.LESS_AND_EQUALITY,
                TokenTypeList.MORE,
                TokenTypeList.MORE_AND_EQUALITY
        );
    }

    public boolean isUnaryOperator(){
        return isType(
                TokenTypeList.IF,
                TokenTypeList.ELSE,
                TokenTypeList.WHILE,
                TokenTypeList.FUNCTION
        );
    }

    public boolean isFinalOperator(){
        return isType(
                TokenTypeList.SEMICOLON,
                TokenTypeList.RIGHT_PAR,
                TokenTypeList.RIGHT_CUR
                );
    }

    public boolean isLogicalOperator() {
        return isType(
                TokenTypeList.TRUE,
                TokenTypeList.FALSE,
                TokenTypeList.EQUALITY,
                TokenTypeList.NOT_EQUALITY,
                TokenTypeList.MORE,
                TokenTypeList.MORE_AND_EQUALITY,
                TokenTypeList.LESS,
                TokenTypeList.LESS_AND_EQUALITY
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

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", position=" + position +
                '}';
    }
}
