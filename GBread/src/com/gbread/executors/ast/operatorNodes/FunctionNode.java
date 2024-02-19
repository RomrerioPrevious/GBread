package com.gbread.executors.ast.operatorNodes;

import com.gbread.executors.ast.Node;
import com.gbread.executors.runner.Runner;

public interface FunctionNode extends Node {
    void run(Runner previousRunner, Node... parameters);
}
