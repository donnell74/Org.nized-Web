package com.msuaitp.orgnized.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.userDetailsService(userDetailsService); }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/home", "/img/**", "/js/**", "/css/**")
		.permitAll()
		.antMatchers("/admin/")
		.hasRole("ADMIN")
		.antMatchers("/announcements", "/attendance", "/classbonus", "/notes", "/people",
				"/profile", "/surveys").hasRole("USER")
				// .access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
				.anyRequest().authenticated().and()
				// ...
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailServiceImpl());
	}

	@Override
	public UserDetailsService userDetailsServiceBean() {
		return new UserDetailServiceImpl();
	}
	/*
	 * public PasswordEncoder passwordEncoder() { return new PasswordEncoder();
	 * }
	 */
}