package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;

public interface ReservedFunctionNode extends Node {
    void run(Node... parameters);
}
