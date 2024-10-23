package com.akashsoam.RuleEngine;

import com.akashsoam.RuleEngine.model.Node;
import com.akashsoam.RuleEngine.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RuleEngineApplicationTests {

    @Autowired
    private RuleService ruleService;

    @Test
    public void testEvaluateRule() {
//        String ruleString = "(age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing') AND (salary > 50000 OR experience > 5)";
//        Node ast = ruleService.createRule(ruleString);
//
//        Map<String, Object> userData1 = new HashMap<>();
//        userData1.put("age", 35);
//        userData1.put("department", "Sales");
//        userData1.put("salary", 60000);
//        userData1.put("experience", 3);
//
//        Map<String, Object> userData2 = new HashMap<>();
//        userData2.put("age", 24);
//        userData2.put("department", "Marketing");
//        userData2.put("salary", 40000);
//        userData2.put("experience", 4);
//
//        assertTrue(ruleService.evaluateRule(ast, userData1));
//        assertFalse(ruleService.evaluateRule(ast, userData2));
    }

    @Test
    void contextLoads() {
    }
}
