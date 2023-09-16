package com.gbread.work.executors;

import com.gbread.work.AST.*;
import com.gbread.work.AST.StatementNode;
import com.gbread.work.AST.operatorNodes.BinaryNode;
import com.gbread.work.AST.operatorNodes.UnaryNode;
import com.gbread.work.tokens.Token;
import com.gbread.work.tokens.TokenType;
import com.gbread.work.tokens.TokenTypeList;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Token[] tokenArray;
    private int position;
    private Map<String, String> scope = new HashMap<>();

    public Parser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public Node parseCode(){
        StatementNode node = new StatementNode();
        Node temp;
        while (position < tokenArray.length){
            temp = parseString();
            node.addNode(temp);
        }
        return node;
    }

    private Node parseString(){
        Token token = tokenArray[position];
        if (token.isUnaryOperator()){
            return unaryParser(token);
        }
        return null;
    }

    private Node unaryParser(Token token) {
        return new UnaryNode(token, null);
    }

    private Node binaryParser(Token token) {
        return new BinaryNode(token, null, null);
    }

    public Token match(TokenType... types){
        Token token = tokenArray[position];
        for (TokenType type: types) {
            if (token.type() == type){
                return token;
            }
        }
        return null;
    }
}
