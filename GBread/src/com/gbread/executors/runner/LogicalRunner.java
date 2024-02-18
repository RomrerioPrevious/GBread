package com.gbread.executors.runner;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ReservedFunctionNode;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenType;

import java.util.HashMap;
import java.util.Map;

public class LogicalRunner {
    private BinaryNode node;

    public LogicalRunner(BinaryNode node) {
        this.node = node;
    }

    public boolean parseLogicallExpressions() {
        if (node.operator.isLogicalOperator()) {
            ReservedFunctionNode expression = getLogicallExpreion(node.operator);
            expression.run();
            return true; // TODO
        }
        return false;
    }
    
    public ReservedFunctionNode getLogicallExpreion(Token token){
        Map<TokenType, ReservedFunctionNode> expreions = getLogicalExpression();
        ReservedFunctionNode node = expreions.get(token.type());
        return node;
    }

    public Map<TokenType, ReservedFunctionNode> getLogicalExpression(){
        HashMap<TokenType, ReservedFunctionNode> logicalExpressions = new HashMap<>();

        return logicalExpressions;
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
        } else {
            throw new SyntaxException();
        }
    }
}
