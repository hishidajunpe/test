package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired 
	private SpringUserService springUserService;

	public Account saveUser(String name,String password){
		springUserService.createUser(name, password);
		
		Account account = new Account();
		account.setUsername(name);
		
//		user.setPassword(password);
		accountRepository.save(account);
		return account;
	}
	
	public User find(String name) {
        return userRepository.findById(name).get();
    }


}
