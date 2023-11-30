package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class NumberNode implements ObjectNode {
    private Token number;

    public NumberNode(Token number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number.text();
    }

    @Override
    public Object returnValue() {
        return null;
    }

    @Override
    public String returnStringValue() {
        return null;
    }
}
