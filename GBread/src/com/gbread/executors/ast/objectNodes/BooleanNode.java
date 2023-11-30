package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class BooleanNode implements ObjectNode {
    public Token bool;

    public BooleanNode(Token bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return bool.text();
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
