package com.example.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

//	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//				.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//				.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
//				.withUser(users.username("abhishek").password("test123").roles("EMPLOYEE", "ADMIN"));
	
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER")
				.antMatchers("/systems/**").hasRole("ADMIN")
				.and()
				.formLogin()
				.loginPage("/login-page")
				.loginProcessingUrl("/authenticate-user")
				.permitAll()
				.and().logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
