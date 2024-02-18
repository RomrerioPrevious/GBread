package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.StatementNode;
import com.gbread.executors.ast.objectNodes.BooleanNode;
import com.gbread.executors.ast.objectNodes.NumberNode;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.List;

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
        if (!tokenArray[0].isType(TokenTypeList.ELSE)){
            List<Token> tokenVariableList = new ArrayList<>();
            while(!tokenArray[position].isType(TokenTypeList.RIGHT_PAR)){
                tokenVariableList.add(tokenArray[position]);
                position++;
            }
            tokenVariableList.add(new Token(TokenTypeList.SEMICOLON.tokenType, ";", tokenVariableList.size()));
            parser = new Parser(tokenVariableList.toArray(tokenVariableList.toArray(new Token[0])));
            variableNode = parser.parseCode().getNodeList().toArray(new Node[0]);
        }
        parser = new Parser(tokenArray, position + 2);
        functionNode = (StatementNode) parser.parseCode();
        return new UnaryNode(tokenArray[0], functionNode, variableNode);
    }
}
/*
while(!tokenArray[position].isType(TokenTypeList.LEFT_CUR)){
        node = tokenArray[position].createNodeFromToken();
        variableNode.add(node);
        position++;
        }
        variableNode.remove(position - 3);
        position++;
*/
