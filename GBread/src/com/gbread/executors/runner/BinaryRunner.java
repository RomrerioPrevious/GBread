package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;

public class BinaryRunner {
    public static boolean equality(BinaryNode node, Runner previousRunner) {
        Object rightValue = getValueFromNode(node.rightNode, previousRunner);
        Object leftValue = getValueFromNode(node.leftNode, previousRunner);
        assert rightValue != null;
        return rightValue.equals(leftValue);
    }

    public static Object getValueFromNode(Node node, Runner previousRunner) {
        if (node instanceof ExecutableFunctionNode || node instanceof BinaryNode) {
            Runner runner = new Runner(node, previousRunner);
            Node variable = runner.run();
            return getValueFromNode(variable, previousRunner);
        } else if (node instanceof ObjectNode) {
            return ((ObjectNode) node).returnValue();
        } else {
            throw new RuntimeException(); // TODO Syntax exception
        }
    }
}
