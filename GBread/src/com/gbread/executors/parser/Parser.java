package com.gbread.executors.parser;

import com.gbread.executors.ast.*;
import com.gbread.executors.ast.StatementNode;
import com.gbread.executors.ast.objectNodes.NumberNode;
import com.gbread.executors.ast.objectNodes.VariableNode;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final Token[] tokenArray;
    private int position;
    private Map<String, String> scope = new HashMap<>();

    public Parser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public Node parseCode() {
        StatementNode node = new StatementNode();
        Node temp;
        try {
            while (position < tokenArray.length) {
                temp = parseString();
                if (temp == null) {
                    throw new RuntimeException(); //TODO create new exception
                }
                node.addNode(temp);
                position++;
            }
        } catch (ArrayIndexOutOfBoundsException exception) { //TODO create error handler
            System.out.println("Position: " + position);
        }

        return node;
    }

    private Node parseString() {
        Token token = tokenArray[position];
        Node node = null;
        if (token.isUnaryOperator()) {
            return unaryParser(token);
        }
        node = findAndReturnBinaryNode();
        return node;
    }

    private Node findAndReturnBinaryNode() {
        Token token;
        Node node = null;
        do {
            position++;
            token = tokenArray[position];
            if (token.isBinaryOperator()) {
                if (node != null) {
                    node = binaryParser(token, node);
                } else {
                    node = binaryParser(token);
                }
            } else if (token.isType(TokenTypeList.LPAR.tokenType)) {
                node = formulaParser();
            }
        } while (!tokenArray[position].isType(TokenTypeList.SEMICOLON.tokenType));
        return node;
    }

    private Node unaryParser(Token token) {
        return new UnaryNode(token, null);
    }

    private Node binaryParser(Token token) {
        return binaryParser(token, createNodeByPosition(position - 1));
    }

    private Node binaryParser(Token token, Node leftNode) {
        position++;
        Node rightNode;
        if (tokenArray[position].isType(TokenTypeList.LPAR.tokenType)) {
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
            if (token.isType(TokenTypeList.LPAR.tokenType)) {
                node = formulaParser();
            } else if (token.isBinaryOperator()) {
                node = binaryParser(token, node);
            } else {
                node = createNodeByPosition(position);
            }
            position++;
        } while (!tokenArray[position].isType(TokenTypeList.RPAR.tokenType));
        return node;
    }

    private Node createNodeByPosition(int position) {
        Token token = tokenArray[position];
        if (token.isType(TokenTypeList.VARIABLE.tokenType)) {
            return new VariableNode(token);
        }
        if (token.isType(TokenTypeList.NUMBER.tokenType)) {
            return new NumberNode(token);
        }
        throw new RuntimeException();
    }
}
