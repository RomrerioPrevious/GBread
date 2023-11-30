package com.gbread.executors.ast.objectNodes;

import com.gbread.executors.ast.Node;

public interface ObjectNode extends Node {
    Object returnValue();
    String returnStringValue();
}
