package com.gbread.work.AST.objectNodes;

import com.gbread.work.AST.Node;
import com.gbread.work.tokens.Token;

public class NumberNode extends Node {
    private Token number;

    public NumberNode(Token number) {
        this.number = number;
    }
}
