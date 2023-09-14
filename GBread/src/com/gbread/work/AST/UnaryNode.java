package com.gbread.work.AST;

import com.gbread.work.tokens.Token;

public class UnaryNode extends Node{
    Token operator;
    Node nextNode;

    public UnaryNode(Token operator, Node nextNode) {
        this.operator = operator;
        this.nextNode = nextNode;
    }
}
