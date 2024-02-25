package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;
import com.gbread.executors.tokens.Token;

import java.util.ArrayList;
import java.util.List;

public class FunctionParser {
    Token[] tokenArray;
    public FunctionParser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public ExecutableFunctionNode parseFunction(){
        List<Node> nodes = new ArrayList<>();
        Token token;
        for (int i = 2; i < tokenArray.length; i++){
            token = tokenArray[i];
            if (!token.isFinalOperator()){
                nodes.add(token.createNodeFromToken());
            }
        }
        token = tokenArray[0];
        ExecutableFunctionNode functionNode = new ExecutableFunctionNode(token.text(), nodes.toArray(new Node[0]), null);
        return functionNode;
    }
}
