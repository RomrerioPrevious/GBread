package com.gbread.executors.runner;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.BooleanNode;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.Map;

public class UnaryRunner {
    public static void gIf(UnaryNode[] ifNodes, Runner previous) {
        boolean flag;
        for (UnaryNode i : ifNodes) {
            if (i.getOperator().isType(TokenTypeList.ELIF, TokenTypeList.IF)) {
                flag = gIf(i, previous);
                if (flag)
                    break;
            } else {
                Runner runner = new Runner(i.getFunctionNode(), previous);
                runner.run();
                break;
            }
        }
    }

    public static boolean gIf(UnaryNode node, Runner previousRunner) {
        Node[] variables = node.getVariableNodes();
        boolean result = false;
        for (Node i : variables)
            result = LogicalRunner.parseLogical(i, previousRunner);
        Runner runner;
        if (result) {
            runner = new Runner(node.getFunctionNode(), previousRunner);
            runner.run();
            return true;
        }
        return false;
    }

    public static void gWhile(UnaryNode node, Runner previousRunner) {
        Node[] condition = node.getVariableNodes();
        Runner runner = new Runner(node.getFunctionNode(), previousRunner);
        boolean result = false;
        while (true) {
            for (Node i : condition) {
                result = LogicalRunner.parseLogical(i, previousRunner);
            }
            if (result) break;
            runner.run();
        }
    }

    public static BooleanNode gNot (UnaryNode node, Runner previousRunner) {
        Runner runner = new Runner(node.getFunctionNode(), previousRunner);
        ObjectNode objectNode = Node.getObjectNodeFromNode(runner.run(), previousRunner);
        boolean bool = LogicalRunner.parseLogical(objectNode, previousRunner);
        return new BooleanNode(!bool);
    }

}
