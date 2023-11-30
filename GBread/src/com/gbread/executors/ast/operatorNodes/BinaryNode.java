package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

public class BinaryNode implements Node {
    public Token operator;
    public Node leftNode;
    public Node rightNode;

    public BinaryNode(Token operator, Node leftNode, Node rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "operator=" + operator +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}
