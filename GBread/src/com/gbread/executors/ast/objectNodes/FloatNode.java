package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

public class FloatNode implements ObjectNode{
    double floatValue = 0.0;

    public FloatNode(Token token) {
        this.floatValue = Double.parseDouble(token.text());
    }

    public FloatNode(double floatValue) {
        this.floatValue = floatValue;
    }

    public FloatNode(int floatValue) {
        this.floatValue = floatValue;
    }

    @Override
    public Object returnValue() {
        return floatValue;
    }

    @Override
    public String returnStringValue() {
        return floatValue + "";
    }

    @Override
    public String toString() {
        return floatValue + "";
    }
}
