package com.gbread.executors.runner;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.objectNodes.StringNode;
import com.gbread.executors.ast.operatorNodes.ReservatedFunctionNode;
import com.gbread.executors.ast.operatorNodes.UnaryNode;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionsList {
    public static List<UnaryNode> functions() {
        List<UnaryNode> functions = new ArrayList<>();
        ReservatedFunctionNode print = (parameters) -> {
            for (Node node : parameters) {
                if (node instanceof ObjectNode)
                    System.out.print(((ObjectNode) node).returnStringValue());
                else
                    continue;
                    // TODO create nodes opener
            }
            System.out.print("\n");
        };
        functions.add(new UnaryNode(TokenTypeList.FUNCTION,
                                    print,
                                    null,
                                    "print"));
        return functions;
    }
}
