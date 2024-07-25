package com.akashsoam.RuleEngine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String type = "operator";  // ensure a default value is set

    @Column(name = "rule_string", nullable = false)
    private String ruleString;

    @Column(name = "ast", columnDefinition = "json")
    private String ast;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public String getAst() {
        return ast;
    }

    public void setAst(String ast) {
        this.ast = ast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}