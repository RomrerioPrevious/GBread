package com.gbread.work.ast.objectNodes;

import com.gbread.work.ast.Node;
import com.gbread.work.tokens.Token;

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
