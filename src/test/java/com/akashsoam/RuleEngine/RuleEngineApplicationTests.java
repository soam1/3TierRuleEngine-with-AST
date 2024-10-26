package com.akashsoam.RuleEngine;

import com.akashsoam.RuleEngine.controller.RuleController;
import com.akashsoam.RuleEngine.model.Rule;
import com.akashsoam.RuleEngine.service.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RuleController.class)
public class RuleEngineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleService ruleService;

    private Rule rule;

    @BeforeEach
    public void setUp() {
        rule = new Rule();
        rule.setId(1L);
        rule.setRuleName("Test Rule");
        rule.setRuleAST("age > 30");
    }

    @Test
    public void testCreateRule() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("ruleName", "Test Rule");
        request.put("ruleString", "age > 30");

        when(ruleService.createRule(any(String.class), any(String.class))).thenReturn(rule);

        mockMvc.perform(post("/ruleEngine/create-rule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ruleName\":\"Test Rule\",\"ruleString\":\"age > 30\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Test Rule\",\"ruleString\":\"age > 30\"}"));
    }

    @Test
    public void testCombineRules() throws Exception {
        when(ruleService.combineRules(anyLong(), anyLong(), any(String.class))).thenReturn(rule);

        mockMvc.perform(post("/ruleEngine/combine-rules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ruleId1\":1,\"ruleId2\":2,\"operator\":\"AND\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Test Rule\",\"ruleString\":\"age > 30\"}"));
    }

    @Test
    public void testEvaluateRule() throws Exception {
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);

        when(ruleService.evaluateRule(anyLong(), any(Map.class))).thenReturn(true);

        mockMvc.perform(post("/ruleEngine/evaluate-rule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ruleId\":1,\"userData\":{\"age\":35}}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetAllRules() throws Exception {
        when(ruleService.getAllRules()).thenReturn(Collections.singletonList(rule));

        mockMvc.perform(get("/ruleEngine/get-all-rules"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Test Rule\",\"ruleString\":\"age > 30\"}]"));
    }
}