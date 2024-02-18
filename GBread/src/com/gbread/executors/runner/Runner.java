package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.*;
import com.gbread.executors.ast.operatorNodes.*;
import com.gbread.executors.runner.libraries.StandardLibrary;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public Map<String, Node> variables = new HashMap<>();
    Node ast;
    Node[] functions;
    Node[] sequence;

    public Runner(Node ast) {
        this.ast = ast;
        this.functions = getAllFunctions();
    }

    public Runner(Node ast, Runner runner) {
        this.ast = ast;
        this.functions = getAllFunctions(runner);
        this.variables = runner.variables;
    }

    private Node[] getAllFunctions() {
        List<Node> functions = findFunctionsInCode();
        functions.addAll(importLibraries());
        return functions.toArray(new Node[0]);
    }

    private Node[] getAllFunctions(Runner runner) {
        List<Node> functions = new ArrayList<>(List.of(runner.functions));
        functions.addAll(importLibraries());
        return functions.toArray(new Node[0]);
    }

    private List<Node> findFunctionsInCode() {
        List<Node> functionList = new ArrayList<>();
        for (Node i : openNode(ast)) {
            if (i instanceof UnaryNode) {
                if (((UnaryNode) i).getOperator().isType(TokenTypeList.FUNCTION)) {
                    functionList.add(i);
                }
            }
        }
        return functionList;
    }

    private List<Node> importLibraries() {
        List<Node> functions = new ArrayList<>();
        functions.addAll(StandardLibrary.functions());
        return functions;
    }

    public Node run() {
        Node returnNode = null;
        sequence = openNode(ast);
        for (int i = 0; i != sequence.length; i++) {
            Node node = sequence[i];
            if (node instanceof UnaryNode) {
                String nameOfFunction = ((UnaryNode) node).getOperator().text();
                switch (nameOfFunction) {
                    case ("if") -> {
                        UnaryNode[] ifNodes = getIfBlock((UnaryNode) node, i);
                        i += ifNodes.length - 1;
                        UnaryRunner.gIf(ifNodes, this);
                    }
                    case ("while") -> UnaryRunner.gWhile((UnaryNode) node, this);
                }
            } else if (node instanceof BinaryNode) {
                String nameOfOperator = ((BinaryNode) node).operator.text();
                switch (nameOfOperator) {
                    case ("==") -> {
                        returnNode = BinaryRunner.equality((BinaryNode) node, this);
                    }
                    case ("!=") -> {
                        returnNode = BinaryRunner.notEquality((BinaryNode) node, this);
                    }
                    case (">") -> {
                        returnNode = BinaryRunner.more((BinaryNode) node, this);
                    }
                    case ("<") -> {
                        returnNode = BinaryRunner.less((BinaryNode) node, this);
                    }
                    case (">=") -> {
                        returnNode = BinaryRunner.moreAndEqualty((BinaryNode) node, this);
                    }
                    case ("<=") -> {
                        returnNode = BinaryRunner.lessAndEqualty((BinaryNode) node, this);
                    }
                    case ("+") -> {
                        returnNode = BinaryRunner.sum((BinaryNode) node, this);
                    }
                    case ("-") -> {
                        returnNode = BinaryRunner.subtraction((BinaryNode) node, this);
                    }
                    case ("/") -> {
                        returnNode = BinaryRunner.division((BinaryNode) node, this);
                    }
                    case ("*") -> {
                        returnNode = BinaryRunner.multiplication((BinaryNode) node, this);
                    } case "=" -> {
                        BinaryRunner.assignment((BinaryNode) node, this);
                    }
                }
            } else {

            }
        }
        return returnNode;
    }

    public UnaryNode[] getIfBlock(UnaryNode node, int i) {
        List<UnaryNode> ifNodes = new ArrayList<>();
        ifNodes.add(node);
        while (true) {
            if (i + 1 == sequence.length) break;
            if (sequence[i + 1] instanceof UnaryNode elseNode) {
                if (elseNode.getOperator().isType(TokenTypeList.ELIF, TokenTypeList.ELSE)) {
                    ifNodes.add(elseNode);
                    i++;
                    if (elseNode.getOperator().isType(TokenTypeList.ELSE))
                        break;
                } else break;
            } else break;
        }
        return ifNodes.toArray(new UnaryNode[0]);
    }

    private static Node[] openNode(Node node) {
        if (node instanceof StatementNode)
            return ((StatementNode) node).getNodeList().toArray(Node[]::new);
        else
            return new Node[]{node};
    }
}
