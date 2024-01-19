package com.gbread.executors.runner.libraries;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.operatorNodes.ReservedFunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.runner.Runner;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.List;

public class StandardLibrary {
    public static List<UnaryNode> functions() {
        List<UnaryNode> functions = new ArrayList<>();
        ReservedFunctionNode print = (parameters) -> {
            for (Node node : parameters) {
                if (node instanceof ObjectNode) {
                    System.out.print(((ObjectNode) node).returnStringValue());
                } else {
                    Runner runner = new Runner(node);
                    runner.run();
                }
                System.out.print("\n");
            }
        };
        functions.add(new UnaryNode(TokenTypeList.FUNCTION,
                print,
                null,
                "print"));
        return functions;
    }
}
