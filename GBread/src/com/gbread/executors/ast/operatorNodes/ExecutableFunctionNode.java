package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

import java.util.Arrays;

public class ExecutableFunctionNode implements Node {
    Token token;
    Node[] variableNodes;

    public ExecutableFunctionNode(Token token, Node[] variableNodes) {
        this.token = token;
        this.variableNodes = variableNodes;
    }

    @Override
    public String toString() {
        return "ExecutableFunctionNode{" +
                "token=" + token +
                ", variableNodes=" + Arrays.toString(variableNodes) +
                '}';
    }
}
