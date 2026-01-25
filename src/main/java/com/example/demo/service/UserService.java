package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // inject the bean from SecurityConfig
      
    @Autowired
    private RoleRepository roleRepository;


    public User registerUser(UserDTO userDTO) {
       
        // Hash password before saving
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        
        // 2. Fetch Role entity from DB
        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 3. Convert Role → Set<Role>
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        
        
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashedPassword);
        user.setRoles(roles);//Linking or Populating the user_roles explicitly here.Only Relationship defining is not sufficient.

       return userRepository.save(user);
    }
}
