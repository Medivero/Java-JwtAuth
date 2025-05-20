package com.example.demo.controllers;

import com.example.demo.classes.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoginUser;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    UserRepository repo;
    BCryptPasswordEncoder bp = new BCryptPasswordEncoder();
    public UserController(UserRepository repo) {
        this.repo = repo;
    }
    @CrossOrigin
    @PostMapping("/registrationUser")
    public ResponseEntity<String> RegistrationUser(@RequestBody User user) throws Exception{
        if (repo.existsUserByUsername(user.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exist");
        }
        else{
            user.setPassword(bp.encode(user.getPassword()));
            repo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully registered");
        }
    }
    @Autowired
    private JwtUtil jwt;

    @PostMapping("/loginUser")
    @CrossOrigin
    public ResponseEntity<?> loginUser(@RequestBody LoginUser user) {
        if (repo.existsUserByUsername(user.getUsername())) {
            User curruser = repo.getUserByUsername(user.getUsername());
            if (bp.matches(user.getPassword(), curruser.getPassword())) {
                String token = jwt.generateToken(user.getUsername());
                return ResponseEntity.ok(Collections.singletonMap("token",token));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }
    @GetMapping("/getUserData")
    public ResponseEntity<User> getUsersData() throws Exception{
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) repo.findUserByUsername(username);
        user.setPassword("");
        return ResponseEntity.ok(user);
    }


}
