package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class VariableNode implements ObjectNode {
    private Token variable;

    public VariableNode(Token variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return variable.text();
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
