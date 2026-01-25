package com.example.demo.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        
	        Set<GrantedAuthority> authorities=user.getRoles().stream()
	        											.flatMap(role->role.getPermissions().stream())
	        											.map(permission->new SimpleGrantedAuthority(permission.getName()))
	        											.collect(Collectors.toSet());
	        											
	      return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	        											
	        											
	    }
}
