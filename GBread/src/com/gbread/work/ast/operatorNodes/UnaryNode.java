package com.gbread.work.ast.operatorNodes;

import com.gbread.work.ast.Node;
import com.gbread.work.tokens.Token;

public class UnaryNode extends Node {
    Token operator;
    Node nextNode;

    @Override
    public String toString() {
        return "UnaryNode{" +
                "operator=" + operator +
                ", nextNode=" + nextNode +
                '}';
    }

    public UnaryNode(Token operator, Node nextNode) {
        this.operator = operator;
        this.nextNode = nextNode;
    }
}
