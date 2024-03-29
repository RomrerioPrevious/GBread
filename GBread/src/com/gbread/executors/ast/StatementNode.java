package com.gbread.executors.ast;

import java.util.ArrayList;
import java.util.List;

public class StatementNode implements Node {
    List<Node> nodeList = new ArrayList<>();

    public void addNode(Node node){
        nodeList.add(node);
    }

    @Override
    public String toString() {
        return "StatementNode{" +
                "nodeList=" + nodeList +
                '}';
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}
