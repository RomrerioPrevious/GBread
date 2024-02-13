package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.Objects;

public class BooleanNode implements ObjectNode {
    public Token bool;

    public BooleanNode(Token bool) {
        this.bool = bool;
    }

    public BooleanNode(boolean bool) {
        if (bool) {
            this.bool = new Token(TokenTypeList.TRUE.tokenType, "true", 0);
        } else {
            this.bool = new Token(TokenTypeList.FALSE.tokenType, "false", 0);
        }
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
