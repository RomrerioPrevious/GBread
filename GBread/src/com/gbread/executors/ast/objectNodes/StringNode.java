package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class StringNode implements ObjectNode {
    public String string;

    public StringNode(Token string) {
        this.string = string.toString();
    }

    public StringNode(String string){
        this.string = string;
    }

    @Override
    public Object returnValue() {
        return string;
    }

    @Override
    public String returnStringValue() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
