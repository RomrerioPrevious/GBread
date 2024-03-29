package com.gbread.executors.parser;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.StatementNode;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.runner.Runner;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnaryParser {
    private final Token[] tokenArray;
    private int position = 2;

    public UnaryParser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public UnaryNode parseUnaryNode() {
        Node[] variableNode = new Node[0];
        StatementNode functionNode;
        Parser parser;
        if (!tokenArray[0].isType(TokenTypeList.ELSE, TokenTypeList.NOT)){
            List<Token> tokenVariableList = new ArrayList<>();
            while(!tokenArray[position].isType(TokenTypeList.RIGHT_PAR)){
                tokenVariableList.add(tokenArray[position]);
                position++;
            }
            tokenVariableList.add(new Token(TokenTypeList.SEMICOLON.tokenType, ";", tokenVariableList.size()));
            parser = new Parser(tokenVariableList.toArray(tokenVariableList.toArray(new Token[0])));
            variableNode = parser.parseCode().getNodeList().toArray(new Node[0]);
            parser = new Parser(tokenArray, position + 2);
        } else if (tokenArray[0].isType(TokenTypeList.ELSE)){
            parser = new Parser(tokenArray, position);
        } else {
            parser = new Parser(tokenArray, 1);
        }
        functionNode = (StatementNode) parser.parseCode();
        return new UnaryNode(tokenArray[0], functionNode, variableNode);
    }
}
