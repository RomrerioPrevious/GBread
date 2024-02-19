package com.gbread.executors.ast;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;
import com.gbread.executors.runner.Runner;

import java.util.Map;

public interface Node {
    static ObjectNode getObjectNodeFromNode(Node node, Runner previousRunner) {
        if (node instanceof ExecutableFunctionNode || node instanceof BinaryNode) {
            Runner runner = new Runner(node, previousRunner);
            Node variable = runner.run();
            return getObjectNodeFromNode(variable, previousRunner);
        } else if(node instanceof VariableNode){
            Map<String, Node> variables = previousRunner.variables;
            return (VariableNode) variables.get(((VariableNode) node).name);
        } else if (node instanceof ObjectNode) {
            return (ObjectNode) node;
        } else {
            throw new SyntaxException();
        }
    }
}
