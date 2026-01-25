package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository; // your JPA repo

@RestController
@RequestMapping("/admin")
public class AdminController {


    
  
    @PreAuthorize("hasAuthority('add-user')")
    @PostMapping("/add-user")
    public String addUser(@RequestBody UserDTO userDTO) {
      
        return "User created successfully!";
    }
}
