package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;

import java.util.Arrays;

public class ExecutableFunctionNode implements Node {
    public String name;
    public Node[] variableNodes;

    public ExecutableFunctionNode(String name, Node[] variableNodes, Node functionNode) {
        this.name = name;
        this.variableNodes = variableNodes;
    }

    @Override
    public String toString() {
        return "ExecutableFunctionNode{" + name + Arrays.toString(variableNodes) + "}";
    }
}
