package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.tokens.Token;


public class VariableNode implements ObjectNode {
    private final String name;
    public ObjectNode value;

    public VariableNode(Token variableToken) {
        this.name = variableToken.text();
    }

    @Override
    public String toString() {
        return name;
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
