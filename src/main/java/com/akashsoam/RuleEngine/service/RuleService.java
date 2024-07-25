package com.akashsoam.RuleEngine.service;

import com.akashsoam.RuleEngine.model.Node;
import com.akashsoam.RuleEngine.model.Rule;
import com.akashsoam.RuleEngine.repository.RuleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Define valid attributes and a basic pattern for rule validation
    private static final Pattern VALID_RULE_PATTERN = Pattern.compile("^[\\w\\s><=()\\-+'ANDOR]+$");
    private static final Set<String> VALID_ATTRIBUTES = Set.of("age", "department", "salary", "experience");

    public Node createRule(String ruleString) {
        validateRuleString(ruleString);
        Node ast = parseRule(ruleString);
        Rule rule = new Rule();
        rule.setRuleString(ruleString);
        rule.setAst(convertNodeToJson(ast));
        ruleRepository.save(rule);
        return ast;
    }

    public Node combineRules(List<String> ruleStrings) {
        for (String ruleString : ruleStrings) {
            validateRuleString(ruleString);
        }
        return combine(ruleStrings);
    }

    public boolean evaluateRule(Node ast, Map<String, Object> userData) {
        return evaluate(ast, userData);
    }

    public Node modifyRule(Long ruleId, String newRuleString) {
        validateRuleString(newRuleString);
        Rule existingRule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new RuntimeException("Rule not found"));

        Node newAst = parseRule(newRuleString);
        existingRule.setRuleString(newRuleString);
        existingRule.setAst(convertNodeToJson(newAst));
        ruleRepository.save(existingRule);
        return newAst;
    }

//    private Node parseRule(String ruleString) {
//        // Implement the parsing logic here
//        // Example parsing logic (simplified, replace with actual implementation)
//        Node root = new Node("operator", "AND");
//        Node left = new Node("operand", "age > 30");
//        Node right = new Node("operand", "department = 'Sales'");
//        root.setLeft(left);
//        root.setRight(right);
//        return root;
//    }

    private Node parseRule(String ruleString) {
        // Implement the parsing logic here
        // This is a simplified example. Real parsing logic should handle all cases.

        // Check for AND, OR, and nested structures, and parse accordingly.
        if (ruleString.contains("AND")) {
            String[] parts = ruleString.split("AND", 2);
            Node root = new Node("operator", "AND");
            root.setLeft(parseRule(parts[0].trim()));
            root.setRight(parseRule(parts[1].trim()));
            return root;
        } else if (ruleString.contains("OR")) {
            String[] parts = ruleString.split("OR", 2);
            Node root = new Node("operator", "OR");
            root.setLeft(parseRule(parts[0].trim()));
            root.setRight(parseRule(parts[1].trim()));
            return root;
        } else {
            // This assumes the rest are simple conditions; you may need more robust parsing here.
            return new Node("operand", ruleString.trim());
        }
    }


    private Node combine(List<String> ruleStrings) {
        // Implement the combination logic here
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

    private void validateRuleString(String ruleString) {
        if (!VALID_RULE_PATTERN.matcher(ruleString).matches()) {
            throw new RuntimeException("Invalid rule format");
        }
        validateAttributes(ruleString);
    }

    private void validateAttributes(String ruleString) {
        for (String attribute : VALID_ATTRIBUTES) {
            if (ruleString.contains(attribute)) {
                return;
            }
        }
        throw new RuntimeException("Invalid attribute used in rule: " + ruleString);
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
