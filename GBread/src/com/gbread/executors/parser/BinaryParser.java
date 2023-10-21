package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.*;
import com.gbread.executors.ast.operatorNodes.*;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

public class BinaryParser {
    private final Token[] tokenArray;
    private int position;

    public BinaryParser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public Node parseBinaryNode() {
        Token token;
        Node node = null;
        do {
            position++;
            token = tokenArray[position];
            if (token.isBinaryOperator()) {
                if (node != null) { // if previous token been formula
                    node = binaryParser(token, node);
                } else {
                    node = binaryParser(token);
                }
            } else if (token.isType(TokenTypeList.LEFT_PAR)) {
                node = formulaParser();
            }
        } while (!tokenArray[position].isType(TokenTypeList.SEMICOLON));
        return node;
    }

    private Node binaryParser(Token token) {
        return binaryParser(token, createNodeByPosition(position - 1));
    }

    private Node binaryParser(Token token, Node leftNode) {
        position++;
        Node rightNode;
        if (tokenArray[position].isType(TokenTypeList.LEFT_PAR)) {
            rightNode = formulaParser();
        } else if (tokenArray[position + 1].isFinalOperator()) {
            rightNode = createNodeByPosition(position);
        } else {
            rightNode = parseBinaryNode();
        }
        return new BinaryNode(token, leftNode, rightNode);
    }

    private Node formulaParser() {
        Token token;
        position++;
        Node node = null;
        do {
            token = tokenArray[position];
            if (token.isType(TokenTypeList.LEFT_PAR)) {
                node = formulaParser();
            } else if (token.isBinaryOperator()) {
                node = binaryParser(token, node);
            } else {
                node = createNodeByPosition(position);
            }
            position++;
        } while (!tokenArray[position].isType(TokenTypeList.RIGHT_PAR));
        return node;
    }

    private Node createNodeByPosition(int position) {
        Token token = tokenArray[position];
        Node node = token.createNodeFromToken();
        if (node != null){
            return node;
        }
        throw new RuntimeException();
    }
}
