package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
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
        for (int i = 1; i < tokenArray.length; i++){
            token = tokenArray[i];
            if (!token.isFinalOperator()){
                nodes.add(token.createNodeFromToken());
            }
        }
        token = deletePar(tokenArray[0]);
        ExecutableFunctionNode functionNode = new ExecutableFunctionNode(token,
                                                                        nodes.toArray(new Node[0]));
        return functionNode;
    }

    private Token deletePar(Token oldToken){
        StringBuilder newName = new StringBuilder(oldToken.text());
        newName.delete(newName.length() - 1, newName.length());
        return new Token(tokenArray[0].type(), newName.toString(), tokenArray[0].position());
    }
}
