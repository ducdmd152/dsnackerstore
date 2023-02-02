package com.ducdmd152.springboot.dsnackerstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	@Bean
	@Autowired
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	    
	    manager.createUser(User.withUsername("minhtuan")
	      .password(passwordEncoder.encode("minhtuan"))
	      .roles("CUSTOMER")
	      .build());
	    manager.createUser(User.withUsername("haanh")
	      .password(passwordEncoder.encode("haanh"))
	      .roles("EMPLOYEE")
	      .build());
	    return manager;
	}
	
	@Bean
	@Autowired
	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(userDetailsService)
	      .passwordEncoder(passwordEncoder)
	      .and()
	      .build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/shop/**").hasRole("CUSTOMER")
			.and()
				.exceptionHandling().accessDeniedPage("/fail")
			.and()
				.formLogin().permitAll()
//			.and()
//				.logout().permitAll()
			;
		
		return http.build();
	}
	
}
