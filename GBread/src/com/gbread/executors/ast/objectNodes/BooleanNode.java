package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

public class BooleanNode extends Node {
    public Token bool;

    public BooleanNode(Token bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return bool.text();
    }
}
