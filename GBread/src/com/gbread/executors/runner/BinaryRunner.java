package com.gbread.executors.runner;

import com.gbread.exceptions.SyntaxException;
import com.gbread.executors.ast.Node;
import com.gbread.executors.ast.objectNodes.*;
import com.gbread.executors.ast.operatorNodes.BinaryNode;
import com.gbread.executors.ast.operatorNodes.ExecutableFunctionNode;

import java.util.Map;

public class BinaryRunner {
    public static void assignment(BinaryNode node, Runner previousRunner) {
        ObjectNode rightValue = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        if (node.leftNode instanceof VariableNode variable) {
            variable.value = rightValue;
            previousRunner.variables.put(variable.name, variable);
            return;
        }
        throw new SyntaxException(node.leftNode.toString(), node.operator.position());
    }

    public static BooleanNode equality(BinaryNode node, Runner previousRunner) {
        ObjectNode rightValue = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftValue = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        return new BooleanNode(rightValue.returnValue().equals(leftValue.returnValue()));
    }

    public static BooleanNode notEquality(BinaryNode node, Runner previousRunner) {
        ObjectNode rightValue = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftValue = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        return new BooleanNode(!rightValue.returnValue().equals(leftValue.returnValue()));
    }

    public static BooleanNode more(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer & rightValue instanceof Integer) {
            return new BooleanNode((Integer) leftValue > (Integer) rightValue);
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static BooleanNode less(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer & rightValue instanceof Integer) {
            return new BooleanNode((Integer) leftValue < (Integer) rightValue);
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static BooleanNode moreAndEqualty(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer & rightValue instanceof Integer) {
            return new BooleanNode((Integer) leftValue >= (Integer) rightValue);
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static BooleanNode lessAndEqualty(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer & rightValue instanceof Integer) {
            return new BooleanNode((Integer) leftValue <= (Integer) rightValue);
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static ObjectNode sum(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof String) {
            if (rightValue instanceof String) {
                return new StringNode((String) leftValue + (String) rightValue);
            } else if (rightValue instanceof Integer) {
                return new StringNode((String) leftValue + (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new StringNode((String) leftValue + (Double) rightValue);
            }
        } else if (leftValue instanceof Integer) {
            if (rightValue instanceof String) {
                return new StringNode((Integer) leftValue + (String) rightValue);
            } else if (rightValue instanceof Integer) {
                return new NumberNode((Integer) leftValue + (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Integer) leftValue + (Double) rightValue);
            }
        } else if (leftValue instanceof Double) {
            if (rightValue instanceof String) {
                return new StringNode((Double) leftValue + (String) rightValue);
            } else if (rightValue instanceof Integer) {
                return new FloatNode((Double) leftValue + (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Double) leftValue + (Double) rightValue);
            }
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static ObjectNode subtraction(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer) {
            if (rightValue instanceof Integer) {
                return new NumberNode((Integer) leftValue - (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Integer) leftValue - (Double) rightValue);
            }
        } else if (leftValue instanceof Double) {
            if (rightValue instanceof Integer) {
                return new FloatNode((Double) leftValue - (Integer) rightValue);
            } else if (rightValue instanceof Float) {
                return new FloatNode((Double) leftValue - (Double) rightValue);
            }
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static ObjectNode division(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer) {
            if (rightValue instanceof Integer) {
                return new NumberNode((Integer) leftValue / (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Integer) leftValue / (Double) rightValue);
            }
        } else if (leftValue instanceof Double) {
            if (rightValue instanceof Integer) {
                return new FloatNode((Double) leftValue / (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Double) leftValue / (Double) rightValue);
            }
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }

    public static ObjectNode multiplication(BinaryNode node, Runner previousRunner) {
        ObjectNode rightNode = Node.getObjectNodeFromNode(node.rightNode, previousRunner);
        ObjectNode leftNode = Node.getObjectNodeFromNode(node.leftNode, previousRunner);
        Object leftValue = leftNode.returnValue();
        Object rightValue = rightNode.returnValue();
        if (leftValue instanceof Integer) {
            if (rightValue instanceof Integer) {
                return new NumberNode((Integer) leftValue * (Integer) rightValue);
            } else if (rightValue instanceof Double) {
                return new FloatNode((Integer) leftValue * (Double) rightValue);
            }
        } else if (leftValue instanceof Double) {
            if (rightValue instanceof Integer) {
                return new FloatNode((Double) leftValue * (Integer) rightValue);
            } else if (rightValue instanceof Float) {
                return new FloatNode((Double) leftValue * (Double) rightValue);
            }
        }
        throw new SyntaxException(node.operator.text(), node.operator.position());
    }
}
