package com.gbread.work.executors;

import com.gbread.work.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parser {
    private Token[] tokens;
    private int position = 0;
    private List<Objects> scope = new ArrayList<>();

    public Parser(Token[] tokens) {
        this.tokens = tokens;
    }

    public void parseCode(){
    }

    public void match(){}

    public void require(){}
}
