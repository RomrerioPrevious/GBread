package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;


public class VariableNode implements ObjectNode {
    public final String name;
    public ObjectNode value;

    public VariableNode(Token variableToken) {
        this.name = variableToken.text();
    }

    @Override
    public Object returnValue() {
        return value.returnValue();
    }

    @Override
    public String returnStringValue() {
        return value.returnStringValue();
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
