package com.example.demo.repositories;

import com.example.demo.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUsername(String login);

    User getUserByUsername(String login);

    User findUserByUsername(String username);
}
