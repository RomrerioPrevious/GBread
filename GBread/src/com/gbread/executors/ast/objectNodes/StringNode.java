package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class StringNode implements ObjectNode {
    public Token string;

    public StringNode(Token string) {
        this.string = string;
    }

    @Override
    public Object returnValue() {
        return string.text();
    }

    @Override
    public String returnStringValue() {
        return string.text();
    }

    @Override
    public String toString() {
        return string.text();
    }
}
