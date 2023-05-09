package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(String name,String password){
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}
}
