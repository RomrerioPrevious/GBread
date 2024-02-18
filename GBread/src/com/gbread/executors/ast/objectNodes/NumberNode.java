package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class NumberNode implements ObjectNode {
    private int number;

    public NumberNode(Token number) {
        this.number = Integer.parseInt(number.text());
    }

    public NumberNode(int number) {this.number = number;}

    @Override
    public Object returnValue() {
        return number;
    }

    @Override
    public String returnStringValue() {
        return number + "";
    }

    @Override
    public String toString() {
        return number + "";
    }
}
