package com.ducdmd152.springboot.dsnackerstore;

import javax.sql.DataSource;

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
	
//	Authentication by accounts in memory
	@Bean
	@Autowired
	public AuthenticationManager authenticationManager(HttpSecurity http, DataSource datasource, PasswordEncoder passwordEncoder) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .jdbcAuthentication().dataSource(datasource)
	      .passwordEncoder(passwordEncoder)
	      .and()
	      .build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
	
//	Authentication by accounts in memory
//	@Bean
//	@Autowired
//	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//	    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	    
//	    manager.createUser(User.withUsername("minhtuan")
//	      .password(passwordEncoder.encode("minhtuan"))
//	      .roles("CUSTOMER")
//	      .build());
//	    manager.createUser(User.withUsername("haanh")
//	      .password(passwordEncoder.encode("haanh"))
//	      .roles("EMPLOYEE")
//	      .build());
//	    return manager;
//	}
//	
//	@Bean
//	@Autowired
//	public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) 
//	  throws Exception {
//	    return http.getSharedObject(AuthenticationManagerBuilder.class)
//	      .userDetailsService(userDetailsService)
//	      .passwordEncoder(passwordEncoder)
//	      .and()
//	      .build();
//	}
//	

	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/registration/**").anonymous()
				.antMatchers("/guest/**").anonymous()
				.antMatchers("/customer/**").hasRole("CUSTOMER")
				.antMatchers("/employee/**").hasRole("EMPLOYEE")
				.antMatchers("/owner/**").hasRole("OWNER")
				.antMatchers("/shop/**").not().hasRole("OWNER")
			.and()
				.exceptionHandling().accessDeniedPage("/fail")
			.and()
				.formLogin()
					.loginPage("/registration/showLogin")
					.loginProcessingUrl("/authenticate")
					.permitAll()
				/*
				 * .and() .formLogin() 
				 * .loginProcessingUrl("authenticate") .permitAll()
				 */
			.and()
				.logout().permitAll()
			;
		
		return http.build();
	}
	
}
