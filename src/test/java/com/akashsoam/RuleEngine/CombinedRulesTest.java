// CombinedRulesTest.java

package com.akashsoam.RuleEngine;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.query.SyntaxException;
import org.junit.jupiter.api.Test;

public class CombinedRulesTest {

    @Test
    public void testCombineTwoSimpleRules() {
        // Test combining two simple rules
        String rule1 = "if (age > 18) then eligible = true;";
        String rule2 = "if (income > 50000) then eligible = true;";
        String combinedRule = combineRules(rule1, rule2);
        assertNotNull(combinedRule);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testCombineMultipleRules() {
        // Test combining multiple rules
        String rule1 = "if (age > 18) then eligible = true;";
        String rule2 = "if (income > 50000) then eligible = true;";
        String rule3 = "if (creditScore > 700) then eligible = true;";
        String combinedRule = combineRules(rule1, rule2, rule3);
        assertNotNull(combinedRule);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testCombineRulesWithConflictingConditions() {
        // Test combining rules with conflicting conditions
        String rule1 = "if (age > 18) then eligible = true;";
        String rule2 = "if (age <= 18) then eligible = false;";
        String combinedRule = combineRules(rule1, rule2);
        assertNotNull(combinedRule);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testCombineRulesWithNestedConditions() {
        // Test combining rules with nested conditions
        String rule1 = "if (age > 18) then if (income > 50000) then eligible = true;";
        String rule2 = "if (creditScore > 700) then eligible = true;";
        String combinedRule = combineRules(rule1, rule2);
        assertNotNull(combinedRule);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testCombineRulesWithInvalidSyntax() {
        // Test combining rules with invalid syntax
        String rule1 = "if (age > 18) then eligible = true;";
        String rule2 = "if (income > 50000 then eligible = true;"; // Missing closing parenthesis
        assertThrows(SyntaxException.class, () -> combineRules(rule1, rule2));
    }

    // Helper method to combine rules (implementation depends on your rule engine)
    private String combineRules(String... rules) {
        // Combine rules logic
        return String.join(" ", rules);
    }
}