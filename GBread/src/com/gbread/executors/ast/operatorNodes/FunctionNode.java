package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.runner.Runner;

import java.util.Arrays;

public interface FunctionNode extends Node {
    String name = null;
    Node[] variableNodes = null;
    Node functionNode = null;

    default Node run(Runner previousRunner, Node... parameters) {return null;}

    default String getName() {
        return name;
    }
    default Node getFunctionNode() {
        return functionNode;
    }
    default Node[] getVariableNodes() {
        return variableNodes;
    }
}
