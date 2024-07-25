package com.akashsoam.RuleEngine.service;

import com.akashsoam.RuleEngine.model.Node;
import com.akashsoam.RuleEngine.model.Rule;
import com.akashsoam.RuleEngine.repository.RuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Node createRule(String ruleString) {
        // Parse ruleString and generate AST
        Node ast = parseRule(ruleString);
        Rule rule = new Rule();
        rule.setRuleString(ruleString);
        rule.setAst(convertNodeToJson(ast));
        ruleRepository.save(rule);
        return ast;
    }

    public Node combineRules(List<String> ruleStrings) {
        // Combine the rules into a single AST
        return combine(ruleStrings);
    }

    public boolean evaluateRule(Node ast, Map<String, Object> userData) {
        // Evaluate the AST against user data
        return evaluate(ast, userData);
    }

    private Node parseRule(String ruleString) {
        // Implement the parsing logic here
        // Example parsing logic
        // This is a simplified example. You should replace this with a real parser.
        Node root = new Node("operator", "AND");
        Node left = new Node("operand", "age > 30");
        Node right = new Node("operand", "department = 'Sales'");
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    private Node combine(List<String> ruleStrings) {
        // Implement the combination logic here
        // Example combination logic
        // This is a simplified example. You should replace this with a real combiner.
        Node root = new Node("operator", "OR");
        for (String ruleString : ruleStrings) {
            Node rule = parseRule(ruleString);
            if (root.getLeft() == null) {
                root.setLeft(rule);
            } else {
                Node newRoot = new Node("operator", "AND");
                newRoot.setLeft(root);
                newRoot.setRight(rule);
                root = newRoot;
            }
        }
        return root;
    }

    private boolean evaluate(Node node, Map<String, Object> userData) {
        // Implement the evaluation logic here
        // Example evaluation logic
        // This is a simplified example. You should replace this with a real evaluator.
        if ("operand".equals(node.getType())) {
            String[] parts = ((String) node.getValue()).split(" ");
            String attribute = parts[0];
            String operator = parts[1];
            String value = parts[2];
            Object userValue = userData.get(attribute);
            if (userValue instanceof Integer) {
                int intValue = Integer.parseInt(value);
                switch (operator) {
                    case ">":
                        return (int) userValue > intValue;
                    case "<":
                        return (int) userValue < intValue;
                    case "=":
                        return userValue.equals(intValue);
                }
            } else if (userValue instanceof String) {
                return userValue.equals(value);
            }
        } else if ("operator".equals(node.getType())) {
            boolean leftResult = evaluate(node.getLeft(), userData);
            boolean rightResult = evaluate(node.getRight(), userData);
            switch ((String) node.getValue()) {
                case "AND":
                    return leftResult && rightResult;
                case "OR":
                    return leftResult || rightResult;
            }
        }
        return false;
    }

    public String convertNodeToJson(Node node) {
        try {
            return objectMapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Node to JSON", e);
        }
    }

    public Node convertJsonToNode(String json) {
        try {
            return objectMapper.readValue(json, Node.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to Node", e);
        }
    }
}
