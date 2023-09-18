package com.gbread.work.ast.objectNodes;

import com.gbread.work.ast.Node;
import com.gbread.work.tokens.Token;

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
