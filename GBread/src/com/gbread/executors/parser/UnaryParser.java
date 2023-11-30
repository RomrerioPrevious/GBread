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
        List<Node> variableNode = new ArrayList<>();
        StatementNode functionNode;
        Node node;
        while(!tokenArray[position].isType(TokenTypeList.LEFT_CUR)){
            node = tokenArray[position].createNodeFromToken();
            variableNode.add(node);
            position++;
        }
        variableNode.remove(position - 3);
        position++;
        Parser parser = new Parser(tokenArray, position);
        functionNode = (StatementNode) parser.parseCode();
        return new UnaryNode(tokenArray[0], functionNode, variableNode.toArray(Node[]::new));
    }
}
