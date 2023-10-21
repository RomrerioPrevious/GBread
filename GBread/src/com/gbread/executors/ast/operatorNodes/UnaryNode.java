package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

import java.util.Arrays;

public class UnaryNode extends Node {
    Token operator;
    Node[] variablesOrConditionsNode;

    Node functionNode;

    @Override
    public String toString() {
        return "UnaryNode{" +
                "operator=" + operator +
                ", variablesOrConditionsNode=" + Arrays.toString(variablesOrConditionsNode) +
                ", functionNode=" + functionNode +
                '}';
    }

    public UnaryNode(Token operator, Node functionNode, Node[] variablesOrConditionsNode) {
        this.operator = operator;
        this.functionNode = functionNode;
        this.variablesOrConditionsNode = variablesOrConditionsNode;
    }
}
