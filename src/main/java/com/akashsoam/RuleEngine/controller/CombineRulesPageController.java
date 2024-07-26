package com.akashsoam.RuleEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akashsoam.RuleEngine.service.RuleService;

import java.util.List;

@Controller
public class CombineRulesPageController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/combine_rules")
    public String showCombineRulesPage() {
        return "combine_rules";
    }

    @PostMapping("/combine_rules")
    public String combineRules(@RequestParam("rule1") String rule1,
                               @RequestParam("rule2") String rule2,
                               Model model) {
        List<String> ruleStrings = List.of(rule1, rule2);
        String combinedRule = ruleService.convertNodeToJson(ruleService.combineRules(ruleStrings));
        model.addAttribute("combinedRule", combinedRule);
        return "combine_rules_result";
    }
}