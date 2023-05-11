package com.example.demo;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//	ログインページの制限なし
		.antMatchers("/login").permitAll()
		.antMatchers("/create").permitAll()
		.antMatchers("/sumAndTable").permitAll()
		.anyRequest().authenticated()
		.and()
		//	ログインページ以後はログインページへ遷移するようにする
		.formLogin()	
		.loginPage("/login").permitAll()
		.and()
		//	ログアウトのpostが飛んで来たらログインページへ遷移する
		.logout()
		.logoutSuccessUrl("/login")
		;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
		web.ignoring().antMatchers("/css/**", "/html/**");

	}	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
}