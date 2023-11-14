package com.security.repo;

import com.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsDao extends JpaRepository<User,String> {
    public User findByUsername(String username);
}
