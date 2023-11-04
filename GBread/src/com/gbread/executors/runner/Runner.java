package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.*;
import com.gbread.executors.ast.operatorNodes.*;
import com.gbread.executors.ast.objectNodes.*;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    StatementNode ast;
    Node[] functions;
    List<Node> variables;

    public Runner(StatementNode ast) {
        this.ast = ast;
        this.functions = findFunctions();
    }

    private Node[] findFunctions(){
        List<Node> functionList = new ArrayList<>();
        for (Node i : openStatementNode(ast)) {
            if (i instanceof UnaryNode) {
                if (((UnaryNode) i).getOperator().isType(TokenTypeList.FUNCTION)) {
                    functionList.add(i);
                }
            }
        }
        return functionList.toArray(new Node[0]);
    }

    public void run(){
        for (Node node : openStatementNode(ast)){
        }
    }

    private static Node[] openStatementNode(StatementNode statementNode){
        return statementNode.getNodeList().toArray(Node[]::new);
    }
}
