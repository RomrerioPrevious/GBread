package com.gbread.executors.runner;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ReservedFunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenType;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.HashMap;
import java.util.Map;

public class LogicalRunner {
    private BinaryNode node;

    public LogicalRunner(BinaryNode node) {
        this.node = node;
    }

    public static boolean parseLogical(Node node, Runner previousRunner) {
        if (node instanceof ObjectNode) {
            Object value = ((ObjectNode) node).returnValue();
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof Integer) {
                return (Integer) value != 0;
            } else if (value instanceof String) {
                return !((String) value).equals("");
            } else {
                throw new SyntaxException();
            }
        } else if (node instanceof BinaryNode) {
            Runner runner = new Runner(node, previousRunner);
            return parseLogical(runner.run(), previousRunner);
        } else if (node instanceof UnaryNode){
            if (((UnaryNode) node).getOperator().isType(TokenTypeList.NOT)) {
                Runner runner = new Runner(((UnaryNode) node).getFunctionNode(), previousRunner);
                ObjectNode objectNode = Node.getObjectNodeFromNode(runner.run(), previousRunner);
                return !parseLogical(objectNode, previousRunner);
            }
        }
        throw new SyntaxException();
    }
}
