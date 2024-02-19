package com.gbread.executors.parser;

import com.gbread.executors.ast.*;
import com.gbread.executors.ast.StatementNode;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.*;

public class Parser {
    private final Token[] tokenArray;
    private int position;

    public Parser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
    }

    public Parser(Token[] tokenArray, int position) {
        this.tokenArray = tokenArray;
        this.position = position;
    }

    public StatementNode parseCode() {
        StatementNode node = new StatementNode();
        Node codeStringNode;
        while (position < tokenArray.length) {
            codeStringNode = parseCodeString();
            if (codeStringNode == null) {
                throw new RuntimeException(); //TODO create new exception
            }
            node.addNode(codeStringNode);
        }
        return node;
    }

    private Node parseCodeString() {
        Token[] codeString = findCodeString();
        if (codeString[0].isUnaryOperator()) {
            UnaryParser parser = new UnaryParser(codeString);
            return parser.parseUnaryNode();
        } else if (codeString[1].isBinaryOperator()) {
            BinaryParser parser = new BinaryParser(codeString);
            return parser.parseBinaryNode();
        } else if (codeString[0].isType(TokenTypeList.FUNCTION_USED)) {
            FunctionParser parser = new FunctionParser(codeString);
            return parser.parseFunction();
        } else {
            return codeString[0].createNodeFromToken();
        }
    }

    private Token[] findCodeString() {
        if (tokenArray[position].isUnaryOperator()) {
            return findUnaryCodeString();
        }
        return findBinaryCodeString();
    }

    private Token[] findBinaryCodeString() {
        List<Token> tokenList = new ArrayList<>();
        while (!tokenArray[position].isType(TokenTypeList.SEMICOLON)) {
            tokenList.add(tokenArray[position]);
            position++;
        }
        tokenList.add(tokenArray[position]);
        position++;
        return tokenList.toArray(Token[]::new);
    }

    private Token[] findUnaryCodeString() {
        List<Token> tokenList = new ArrayList<>();
        int curlyBraceCounter = 1;
        while (!tokenArray[position].isType(TokenTypeList.LEFT_CUR, TokenTypeList.SEMICOLON)) {
            tokenList.add(tokenArray[position]);
            position++;
        }
        if (tokenArray[0].isType(TokenTypeList.NOT)) {
            tokenList.add(new Token(TokenTypeList.SEMICOLON.tokenType, ";", tokenList.size()));
            position++;
            return tokenList.toArray(Token[]::new);
        }
        while (curlyBraceCounter != 0) {
            tokenList.add(tokenArray[position]);
            position++;
            if (tokenArray[position].isType(TokenTypeList.LEFT_CUR)) {
                curlyBraceCounter++;
            } else if (tokenArray[position].isType(TokenTypeList.RIGHT_CUR)) {
                curlyBraceCounter--;
            }
        }
        position++;
        return tokenList.toArray(Token[]::new);
    }
}
