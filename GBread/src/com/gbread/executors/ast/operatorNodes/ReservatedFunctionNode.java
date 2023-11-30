package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;

public interface ReservatedFunctionNode extends Node {
    void run(Node[] parameters);
}
