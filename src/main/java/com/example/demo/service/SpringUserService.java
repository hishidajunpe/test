package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;



@Service
public class SpringUserService {

	@Autowired UserDetailsManager userDetailsManager;

	public void createUser(String username,String password) {
		UserBuilder builder = User.withDefaultPasswordEncoder();
		System.out.println(5);
		UserDetails userDetails = 
				builder
				.username(username)
				.roles("USER")
				.password(password)
				.disabled(false)
				.build();
		System.out.println(6);
		userDetailsManager.createUser(userDetails);
		System.out.println(7);
	}

		public void updateSpringUser(String username, String password) {
			UserDetails userDetails = userDetailsManager.loadUserByUsername(username);

			UserBuilder builder;
			// 新規パスワードが渡された場合はそれを設定する。渡されていない場合は元のパスワードを設定する。
			if (!password.equals("")) {
				builder = User.withDefaultPasswordEncoder();
				builder.password(password);
			} else {
				builder = User.builder();
				builder.password(userDetails.getPassword());
			}

			UserDetails newUserDetails = 
					builder
					.username(username)
					.authorities(userDetails.getAuthorities())
					.disabled(false)
					.build();
			userDetailsManager.updateUser(newUserDetails);
		}

}
