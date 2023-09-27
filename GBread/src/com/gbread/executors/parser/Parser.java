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

    public Node parseCode() {
        StatementNode node = new StatementNode();
        Node temp;
        while (position < tokenArray.length) {
            temp = parseBlock();
            if (temp == null) {
                throw new RuntimeException(); //TODO create new exception
            }
            node.addNode(temp);
        }
        return node;
    }

    private Node parseBlock() {
        Token[] codeString = findCodeString();
        if (codeString[0].isUnaryOperator()) {
//            return unaryParser(); TODO Create unary parser
        }
        BinaryParser parser = new BinaryParser(codeString);
        return parser.findAndReturnBinaryNode();
    }

    private Token[] findCodeString() {
        List<Token> tokenList = new ArrayList<>();
        while (!tokenArray[position].isType(TokenTypeList.SEMICOLON)) {
            tokenList.add(tokenArray[position]);
            position++;
        }
        tokenList.add(tokenArray[position]);
        position++;
        return tokenList.toArray(Token[]::new);
    }
}
