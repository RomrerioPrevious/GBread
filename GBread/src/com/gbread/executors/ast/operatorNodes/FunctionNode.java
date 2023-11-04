package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.tokens.Token;

public class FunctionNode {
    Token token;
    Node function;

    @Override
    public String toString() {
        return "FunctionNode{" +
                "token=" + token +
                ", function=" + function +
                '}';
    }

    public FunctionNode(Token token, Node function) {
        this.token = token;
        this.function = function;
    }
}
