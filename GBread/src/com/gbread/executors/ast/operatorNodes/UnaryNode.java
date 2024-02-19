package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.Arrays;

public class UnaryNode implements Node {
    Token operator;
    Node[] variableNodes;
    Node functionNode;

    public UnaryNode(Token operator, Node functionNode, Node[] variableNodes) {
        this.operator = operator;
        this.functionNode = functionNode;
        this.variableNodes = variableNodes;
    }

    public UnaryNode(TokenTypeList operator, Node functionNode, Node[] variableNodes, String name) {
        this.operator = new Token(operator.tokenType, name, 0);
        this.functionNode = functionNode;
        this.variableNodes = variableNodes;
    }

    public Token getOperator() {
        return operator;
    }

    public Node[] getVariableNodes() {
        return variableNodes;
    }

    public Node getFunctionNode() {
        return functionNode;
    }

    @Override
    public String toString() {
        return "UnaryNode{" +
                "operator=" + operator +
                ", variableNodes=" + Arrays.toString(variableNodes) +
                ", functionNode=" + functionNode +
                '}';
    }
}
