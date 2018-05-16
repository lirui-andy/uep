package com.yichang.uep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yichang.uep.service.UepUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UepUserDetailsService userService;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
			.disable()
		.authorizeRequests()
			.antMatchers("/","/home","/**/*.css", "/**/*.js","/**/*.ico",
					"/bower_components/**/*","/dist/**/*","/js/**/*",
					"node_modules/**/*","/plugins/**/*","/**/*.png","/**/*.exe","/**/*.zip","/**/*.rar").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/list")
			.permitAll()
			.and()
		.logout()
			.permitAll()
		;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	protected UserDetailsService userDetailsService() {
//		JdbcDaoImpl j = new JdbcDaoImpl();
//		j.setJdbcTemplate(jdbcTemplate);
//		return j;
//	}

	
}
