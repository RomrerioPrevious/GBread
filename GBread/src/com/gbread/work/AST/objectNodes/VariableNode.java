package com.gbread.work.AST.objectNodes;

import com.gbread.work.AST.Node;
import com.gbread.work.tokens.Token;

public class VariableNode extends Node {
    private Token variable;

    public VariableNode(Token variable) {
        this.variable = variable;
    }
}
