package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.*;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.operatorNodes.*;
import com.gbread.executors.runner.libraries.StandardLibrary;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public Map<String, Node> variables = new HashMap<>();
    Map<String, FunctionNode> functions;
    Node ast;
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

    private Map<String, FunctionNode> getAllFunctions() {
        Map<String, FunctionNode> functions = findFunctionsInCode();
        functions.putAll(importLibraries());
        return functions;
    }

    private Map<String, FunctionNode> getAllFunctions(Runner runner) {
        Map<String, FunctionNode> functions = new HashMap<>(runner.functions);
        functions.putAll(importLibraries());
        return functions;
    }

    private Map<String, FunctionNode> findFunctionsInCode() {
        Map<String, FunctionNode> functionList = new HashMap<>();
        for (Node i : openNode(ast)) {
            if (i instanceof UnaryNode) {
                if (((UnaryNode) i).getOperator().isType(TokenTypeList.FUNCTION)) {
                }
            }
        }
        return functionList;
    }

    private Map<String, FunctionNode> importLibraries() {
        Map<String, FunctionNode> functions = StandardLibrary.functions(); // TODO make import
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
                    case ("not") -> returnNode = UnaryRunner.gNot((UnaryNode) node, this);
                }
            } else if (node instanceof BinaryNode) {
                String nameOfOperator = ((BinaryNode) node).operator.text();
                switch (nameOfOperator) {
                    case ("==") -> returnNode = BinaryRunner.equality((BinaryNode) node, this);
                    case ("!=") -> returnNode = BinaryRunner.notEquality((BinaryNode) node, this);
                    case (">") -> returnNode = BinaryRunner.more((BinaryNode) node, this);
                    case ("<") -> returnNode = BinaryRunner.less((BinaryNode) node, this);
                    case (">=") -> returnNode = BinaryRunner.moreAndEqualty((BinaryNode) node, this);
                    case ("<=") -> returnNode = BinaryRunner.lessAndEqualty((BinaryNode) node, this);
                    case ("+") -> returnNode = BinaryRunner.sum((BinaryNode) node, this);
                    case ("-") -> returnNode = BinaryRunner.subtraction((BinaryNode) node, this);
                    case ("/") -> returnNode = BinaryRunner.division((BinaryNode) node, this);
                    case ("*") -> returnNode = BinaryRunner.multiplication((BinaryNode) node, this);
                    case "=" -> BinaryRunner.assignment((BinaryNode) node, this);
                }
            } else if (node instanceof ObjectNode){
                returnNode = node;
            } else if (node instanceof ExecutableFunctionNode) {
                FunctionNode function = functions.get(((ExecutableFunctionNode) node).name);
                function.run(this, ((ExecutableFunctionNode) node).variableNodes);
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
