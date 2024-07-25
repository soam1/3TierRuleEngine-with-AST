package com.akashsoam.RuleEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvaluatePageController {

    @GetMapping("/evaluate_rule")
    public String showEvaluateRulePage() {
        return "evaluate_rule";
    }
}
