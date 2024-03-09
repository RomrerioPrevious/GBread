package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;
import com.gbread.executors.ast.operatorNodes.FunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.runner.Runner;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.List;

public class FunctionParser {
    Token[] tokenArray;
    public FunctionParser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public FunctionNode parseFunction() {
        String functionName = tokenArray[1].text();
        List<VariableNode> variables = new ArrayList<>();
        Token token;
        for (int i = 3; i != tokenArray.length; i++) {
            token = tokenArray[i];
            if (token.isType(TokenTypeList.RIGHT_PAR))
                break;
            else if (token.isType(TokenTypeList.VARIABLE))
                variables.add((VariableNode) token.createNodeFromToken());
        }
        Parser parser = new Parser(tokenArray, 5 + variables.size());
        FunctionNode func = new FunctionNode() {
            public final String name = functionName;
            public final Node[] variableNodes = variables.toArray(new Node[0]);
            public final Node functionNode = parser.parseCode();

            @Override
            public Node run(Runner previousRunner, Node... parameters) {
                Runner runner = new Runner(functionNode, previousRunner);
                Node returnValue = runner.run();
                return returnValue;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Node getFunctionNode() {
                return functionNode;
            }

            @Override
            public Node[] getVariableNodes() {
                return variableNodes;
            }
        };
        return func;
    }

    public ExecutableFunctionNode parseExecutableFunction(){
        List<Node> nodes = new ArrayList<>();
        Token token;
        for (int i = 2; i < tokenArray.length; i++){
            token = tokenArray[i];
            if (!token.isFinalOperator()){
                nodes.add(token.createNodeFromToken());
            }
        }
        token = tokenArray[0];
        ExecutableFunctionNode functionNode = new ExecutableFunctionNode(token.text(), nodes.toArray(new Node[0]));
        return functionNode;
    }
}
