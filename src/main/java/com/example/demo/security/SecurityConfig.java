package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

	// Lets' define Roles
	@Bean
	public UserDetailsService users() {

		UserDetails admin = User.withUsername("Ram_admin").password("{noop}Ram123").roles("ADMIN").build();

		UserDetails customer = User.withUsername("Shyam_admin").password("{noop}Shyam123").roles("CUSTOMER").build();

		return new InMemoryUserDetailsManager(admin, customer);

	}

	// Let's disable csrf

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html")
				.permitAll()
                .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
