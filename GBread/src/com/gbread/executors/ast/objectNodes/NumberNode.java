package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

public class NumberNode extends Node {
    private Token number;

    public NumberNode(Token number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number.text();
    }
}
