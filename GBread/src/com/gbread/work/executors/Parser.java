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
        }
        catch (ArrayIndexOutOfBoundsException exception){
            System.out.println(position);
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
        Token token = tokenArray[position];
        Node node = null;
        Node temp = null;
        while (!token.isType(TokenTypeList.SEMICOLON.tokenType)) {
            position++;
            token = tokenArray[position];
            if (token.isBinaryOperator()) {
                if (node == null & temp == null) {
                    node = binaryParser(token);
                } else if (node == null) {
                    node = binaryParser(token, temp);
                } else {
                    node = binaryParser(token, node);
                }
            } else if (token.isType(TokenTypeList.LPAR.tokenType)) {
                temp = formulaParser();
            }
        }
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
        } else if (tokenArray[position + 1].isType(TokenTypeList.SEMICOLON.tokenType)){
            rightNode = createNodeByPosition(position);
        } else {
            rightNode = findAndReturnBinaryNode();
        }
        return new BinaryNode(token, leftNode, rightNode);
    }

    private Node formulaParser() {
        Token token = tokenArray[position];
        position++;
        Node node = null;
        Node previousNode = null;
        while (token.type() != TokenTypeList.RPAR.tokenType) {
            token = tokenArray[position];
            if (token.type() == TokenTypeList.LPAR.tokenType) {
                previousNode = formulaParser();
            } else if (token.isBinaryOperator()) {
                node = binaryParser(token, previousNode);
            } else {
                previousNode = createNodeByPosition(position);
            }
            position++;
        }
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
