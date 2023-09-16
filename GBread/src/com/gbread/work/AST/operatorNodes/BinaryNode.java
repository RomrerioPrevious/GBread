package com.gbread.work.AST.operatorNodes;

import com.gbread.work.AST.Node;
import com.gbread.work.tokens.Token;

public class BinaryNode extends Node {
    Token operator;
    Node leftNode;
    Node rightNode;

    public BinaryNode(Token operator, Node leftNode, Node rightNode) {
        this.operator = operator;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
