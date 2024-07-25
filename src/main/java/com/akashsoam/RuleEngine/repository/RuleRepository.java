package com.akashsoam.RuleEngine.repository;

import com.akashsoam.RuleEngine.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
