package com.akashsoam.RuleEngine.repository;

import com.akashsoam.RuleEngine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
