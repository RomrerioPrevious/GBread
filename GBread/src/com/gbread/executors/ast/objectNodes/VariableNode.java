package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

public class VariableNode extends Node {
    private Token variable;

    public VariableNode(Token variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return variable.text();
    }
}
