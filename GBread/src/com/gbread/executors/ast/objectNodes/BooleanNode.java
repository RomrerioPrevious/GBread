package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.Objects;

public class BooleanNode implements ObjectNode {
    public boolean bool;

    public BooleanNode(Token bool) {
        this.bool = Boolean.parseBoolean(bool.text());
    }

    public BooleanNode(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Boolean returnValue() {
        return bool;
    }

    @Override
    public String returnStringValue() {
        return bool + "";
    }

    @Override
    public String toString() {
        return bool + "";
    }
}
