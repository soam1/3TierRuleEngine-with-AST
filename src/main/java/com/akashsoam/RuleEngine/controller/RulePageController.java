package com.akashsoam.RuleEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RulePageController {

    @GetMapping("/create_rule")
    public String showCreateRulePage() {
        return "create_rule";
    }
}
