package com.gbread.work.AST;

import java.util.ArrayList;
import java.util.List;

public class StatementNode extends Node{
    List<Node> nodeList = new ArrayList<>();

    public void addNode(Node node){
        nodeList.add(node);
    }
}
