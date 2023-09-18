package com.gbread.work.ast.operatorNodes;

import com.gbread.work.ast.Node;
import com.gbread.work.tokens.Token;

public class BinaryNode extends Node {
    Token operator;
    Node leftNode;
    Node rightNode;

    @Override
    public String toString() {
        return "BinaryNode{" +
                "operator=" + operator +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }

    public BinaryNode(Token operator, Node leftNode, Node rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
