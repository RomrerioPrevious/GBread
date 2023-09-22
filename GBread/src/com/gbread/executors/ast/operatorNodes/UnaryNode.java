package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

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
