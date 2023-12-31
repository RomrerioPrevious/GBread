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

    public Node findAndReturnBinaryNode() {
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
            } else if (token.isType(TokenTypeList.LPAR)) {
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
        if (tokenArray[position].isType(TokenTypeList.LPAR)) {
            rightNode = formulaParser();
        } else if (tokenArray[position + 1].isFinalOperator()) {
            rightNode = createNodeByPosition(position);
        } else {
            rightNode = findAndReturnBinaryNode();
        }
        return new BinaryNode(token, leftNode, rightNode);
    }

    private Node formulaParser() {
        Token token;
        position++;
        Node node = null;
        do {
            token = tokenArray[position];
            if (token.isType(TokenTypeList.LPAR)) {
                node = formulaParser();
            } else if (token.isBinaryOperator()) {
                node = binaryParser(token, node);
            } else {
                node = createNodeByPosition(position);
            }
            position++;
        } while (!tokenArray[position].isType(TokenTypeList.RPAR));
        return node;
    }

    private Node createNodeByPosition(int position) {
        Token token = tokenArray[position];
        if (token.isType(TokenTypeList.VARIABLE)) {
            return new VariableNode(token);
        }
        if (token.isType(TokenTypeList.NUMBER)) {
            return new NumberNode(token);
        }
        throw new RuntimeException();
    }
}
