package com.gbread.executors.parser;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.StatementNode;
import com.gbread.executors.ast.operatorNodes.*;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryParser {
    int position = 0;
    List<Token[]> blocks;
    List<Token> block;
    List<Token> operators;
    Token[] tokenArray;

    public BinaryParser(Token[] tokenArray) {
        this.tokenArray = tokenArray;
        this.blocks = new ArrayList<>();
        this.block = new ArrayList<>();
        this.operators = new ArrayList<>();
    }

    public BinaryNode parseBinaryNode() {
        Token token;
        while (!tokenArray[position].isType(TokenTypeList.SEMICOLON)) {
            token = tokenArray[position];
            position++;
            if (token.isBinaryOperator()) {
                addBlock(token);
                block = new ArrayList<>();
            } else if (token.isType(TokenTypeList.RIGHT_PAR)) {
                block.add(token);
                addBlock(token);
                block = new ArrayList<>();
            } else {
                block.add(token);
            }
        }
        if (block.size() != 0) {
            addBlock(tokenArray[position]);
            operators.remove(operators.size() - 1);
        }
        return createBinaryNodes();
    }

    private void addBlock(Token token) {
        if (block.get(0).isType(TokenTypeList.FUNCTION_USED)) {
            block.add(token);
        } else {
            operators.add(token);
        }
        block.add(new Token(TokenTypeList.SEMICOLON.tokenType, ";", 0));
        blocks.add(block.toArray(new Token[0]));
    }

    private BinaryNode createBinaryNodes() {
        Node[] blockNodes = createNodesFromBlocks();
        return createBinaryNodeFromBlocks(blockNodes);
    }

    private Node[] createNodesFromBlocks() {
        Node[] blockNodes = new Node[blocks.size()];
        Parser parser;
        position = 0;
        for (Token[] workBlock : blocks) {
            parser = new Parser(workBlock);
            StatementNode statementNode = parser.parseCode();
            blockNodes[position] = statementNode.getNodeList().get(0);
            position++;
        }
        return blockNodes;
    }

    private BinaryNode createBinaryNodeFromBlocks(Node[] blocksNodes) {
        Map<String, Integer> priority = getPriority();
        int indexOfMaxPriority = 0;
        int rightNodePosition = 0;
        BinaryNode node = null;
        while (operators.size() != 0) {
            indexOfMaxPriority = getIndexOfMaxPriority(priority, indexOfMaxPriority);
            for (int i = indexOfMaxPriority + 1; rightNodePosition == 0; i++) {
                if (blocksNodes[i] != null)
                    rightNodePosition = i;
            }
            node = new BinaryNode(operators.get(indexOfMaxPriority),
                    blocksNodes[indexOfMaxPriority],
                    blocksNodes[rightNodePosition]);
            operators.remove(indexOfMaxPriority);
            blocksNodes[indexOfMaxPriority] = node;
            blocksNodes[rightNodePosition] = null;
            rightNodePosition = 0;
        }
        return node;
    }

    private int getIndexOfMaxPriority(Map<String, Integer> priority, int indexOfMaxPriority) {
        if (operators.size() == 1) return 0;
        int maxPriority = 0;
        int tempPriority;
        for (int i = 0; i != operators.size(); i++) {
            tempPriority = priority.get(operators.get(i).text());
            if (tempPriority > maxPriority) {
                maxPriority = tempPriority;
                indexOfMaxPriority = i;
            }
        }
        return indexOfMaxPriority;
    }

    private static Map<String, Integer> getPriority(){
        Map<String, Integer> priority = new HashMap<>();
        priority.put("=", 0);
        priority.put("==", 0);
        priority.put("!=", 0);
        priority.put(">", 0);
        priority.put("<", 0);
        priority.put(">=", 0);
        priority.put("<=", 0);

        priority.put("+", 1);
        priority.put("-", 1);

        priority.put("*", 2);
        priority.put("/", 2);

        priority.put(")", 3);
        return priority;
    }

    private static void swapBinaryNodes(BinaryNode fallingNode, BinaryNode risingNode) {
        Node temp;
        if (fallingNode.rightNode == risingNode) {
            temp = risingNode.leftNode;
            fallingNode.rightNode = temp;
            risingNode.leftNode = fallingNode;
        } else {
            temp = risingNode.rightNode;
            fallingNode.leftNode = temp;
            risingNode.rightNode = fallingNode;
        }
    }
}
