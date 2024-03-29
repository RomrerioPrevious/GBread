package com.gbread.executors.runner.libraries;

import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.ObjectNode;
import com.gbread.executors.ast.objectNodes.StringNode;
import com.gbread.executors.ast.operatorNodes.FunctionNode;
import com.gbread.executors.runner.Runner;

import java.util.*;

public class StandardLibrary {
    public static Map<String, FunctionNode> functions() {
        Map<String, FunctionNode> functions = new HashMap<>();
        FunctionNode print = new FunctionNode() {
            @Override
            public Node run(Runner previousRunner, Node... parameters) {
                Runner runner;
                ObjectNode temp;
                for (Node node : parameters) {
                    runner = new Runner(node, previousRunner);
                    temp = Node.getObjectNodeFromNode(runner.run(), previousRunner);
                    System.out.print(temp.returnStringValue());
                }
                System.out.println();
                return null;
            }
        };
        functions.put("print", print);
        FunctionNode input = new FunctionNode() {
            @Override
            public Node run(Runner previousRunner, Node... parameters) {
                Runner runner;
                ObjectNode temp;
                for (Node node : parameters) {
                    runner = new Runner(node, previousRunner);
                    temp = Node.getObjectNodeFromNode(runner.run(), previousRunner);
                    System.out.print(temp.returnStringValue());
                }
                Scanner scanner = new Scanner(System.in);
                return new StringNode(scanner.nextLine());
            }
        };
        functions.put("input", input);
        return functions;
    }
}
