package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.TokenTypeList;

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

    public static boolean gIf(UnaryNode node, Runner previous) {
        Node[] variables = node.getVariableNodes();
        boolean result = false;
        for (Node i : variables)
            result = LogicalRunner.parseLogical(i);
        Runner runner;
        if (result) {
            runner = new Runner(node.getFunctionNode(), previous);
            runner.run();
            return true;
        }
        return false;
    }

    public static void gWhile(UnaryNode node, Runner previous) {
        Node[] condition = node.getVariableNodes();
        Runner runner = new Runner(node.getFunctionNode(), previous);
        boolean result = false;
        while (true) {
            for (Node i : condition) {
                result = LogicalRunner.parseLogical(i);
            }
            if (result) break;
            runner.run();
        }
    }
}
