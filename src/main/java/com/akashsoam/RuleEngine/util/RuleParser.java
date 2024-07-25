//package com.akashsoam.RuleEngine.util;
//
//import com.akashsoam.RuleEngine.model.Node;
//
//import java.util.Map;
//
//
//public class RuleParser {
//    public static Node parse(String rule) {
//        //parse the ruleString and return the AST
////        implement the parsing logic to convert rule string to AST
//
//
//        return null;
//    }
//
//    public static boolean evaluate(Node root, Map<String, Object> data) {
//        //evaluate the AST with the data and return the result
//        //implement the evaluation logic
//        if (root == null) return false;
//        switch (root.getType()) {
//            case "operand":
//                String[] parts = root.getValue().split(" ");
//                String attribute = parts[0];
//                String operator = parts[1];
//                String value = parts[2];
//                Object attributeValue = data.get(attribute);
//                return evaluateCondition(attributeValue, operator, value);
//            case "operator":
//                boolean leftResult = evaluate(root.getLeft(), data);
//                boolean rightResult = evaluate(root.getRight(), data);
//                return root.getValue().equals("AND") ? leftResult && rightResult : leftResult || rightResult;
//            default:
//                throw new IllegalArgumentException("Invalid node type");
//        }
//    }
//
//    private static boolean evaluateCondition(Object attributeValue, String operator, String value) {
//        int intValue = Integer.parseInt(value);
//
//        switch (operator) {
//            case "==":
//                return attributeValue.equals(value);
//            case "!=":
//                return !attributeValue.equals(value);
//            case ">":
//                return (int) attributeValue > intValue;
//            case "<":
//                return (int) attributeValue < intValue;
//            case ">=":
//                return (int) attributeValue >= intValue;
//            case "<=":
//                return (int) attributeValue <= intValue;
//            default:
//                throw new IllegalArgumentException("Invalid operator");
//        }
//    }
//}
