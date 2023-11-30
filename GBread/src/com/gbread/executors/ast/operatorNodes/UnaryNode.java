package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenType;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.Arrays;

public class UnaryNode implements Node {
    Token operator;
    Node[] variablesOrConditionsNode;

    Node functionNode;

    public UnaryNode(Token operator, Node functionNode, Node[] variableNodes) {
        this.operator = operator;
        this.functionNode = functionNode;
        this.variablesOrConditionsNode = variableNodes;
    }

    public UnaryNode(TokenTypeList operator, Node functionNode, Node[] variableNodes, String name) {
        this.operator = new Token(operator.tokenType, name, 0);
        this.functionNode = functionNode;
        this.variablesOrConditionsNode = variableNodes;
    }

    public Token getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "UnaryNode{" +
                "operator=" + operator +
                ", variablesOrConditionsNode=" + Arrays.toString(variablesOrConditionsNode) +
                ", functionNode=" + functionNode +
                '}';
    }
}
