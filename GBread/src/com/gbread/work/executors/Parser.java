package com.gbread.work.executors;

import com.gbread.work.ast.*;
import com.gbread.work.ast.StatementNode;
import com.gbread.work.ast.objectNodes.NumberNode;
import com.gbread.work.ast.objectNodes.VariableNode;
import com.gbread.work.ast.operatorNodes.BinaryNode;
import com.gbread.work.ast.operatorNodes.UnaryNode;
import com.gbread.work.tokens.Token;
import com.gbread.work.tokens.TokenTypeList;

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
        } catch (ArrayIndexOutOfBoundsException exception) {
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
        Token iterationToken = tokenArray[position];
        Node rightNode;
        if (iterationToken.isType(TokenTypeList.LPAR.tokenType)) {
            rightNode = formulaParser();
        } else if (tokenArray[position + 1].isType(TokenTypeList.SEMICOLON.tokenType)) {
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
        Node previousNode = null;
        do {
            token = tokenArray[position];
            if (token.type() == TokenTypeList.LPAR.tokenType) {
                previousNode = formulaParser();
            } else if (token.isBinaryOperator()) {
                node = binaryParser(token, previousNode);
            } else {
                previousNode = createNodeByPosition(position);
            }
            position++;
        } while (token.type() != TokenTypeList.RPAR.tokenType);
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
