package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;

import java.util.Objects;

public class BooleanNode implements ObjectNode {
    public Token bool;

    public BooleanNode(Token bool) {
        this.bool = bool;
    }

    @Override
    public Boolean returnValue() {
        return Objects.equals(bool.text(), "true");
    }

    @Override
    public String returnStringValue() {
        return null;
    }

    @Override
    public String toString() {
        return bool.text();
    }
}
