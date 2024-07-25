package com.akashsoam.RuleEngine.controller;

import com.akashsoam.RuleEngine.model.Node;
import com.akashsoam.RuleEngine.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    //    @PostMapping("/create_rule")
//    public Node createRule(@RequestParam String rule) {
//        return ruleService.createRule(rule);
//    }
    @PostMapping("/create_rule")
    public Node createRule(@RequestBody Map<String, String> payload) {
        String ruleString = payload.get("rule");
        return ruleService.createRule(ruleString);
    }


    @PostMapping("/combine_rules")
    public Node combineRules(@RequestBody List<String> rules) {
        return ruleService.combineRules(rules);
    }


//    @PostMapping("/evaluate_rule")
//    public boolean evaluateRule(@RequestBody Map<String, Object> userData, @RequestParam String ast) {
//        // Convert JSON string to Node object
//        Node astNode = ruleService.convertJsonToNode(ast);
//        return ruleService.evaluateRule(astNode, userData);
//    }

    @PostMapping("/evaluate_rule")
    public boolean evaluateRule(@RequestBody Map<String, Object> payload) {
        Map<String, Object> userData = (Map<String, Object>) payload.get("userData");
        ObjectMapper objectMapper = new ObjectMapper();
        Node ast = objectMapper.convertValue(payload.get("ast"), Node.class);
        return ruleService.evaluateRule(ast, userData);
    }


}
