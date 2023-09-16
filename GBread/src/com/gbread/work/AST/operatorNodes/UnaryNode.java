package com.gbread.work.AST.operatorNodes;

import com.gbread.work.AST.Node;
import com.gbread.work.tokens.Token;

public class UnaryNode extends Node {
    Token operator;
    Node nextNode;

    public UnaryNode(Token operator, Node nextNode) {
        this.operator = operator;
        this.nextNode = nextNode;
    }
}
