package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.AccountForm;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;


	// ログイン画面へ遷移する
	@GetMapping("/login")
	public String login(@Valid AccountForm accountForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(1);
			return "/login";
		}
		return "redirect:/";
	}

	@PostMapping("/login")
	public String index() {
		return "/";
	}

	// 新規登録画面へ遷移する
	@GetMapping("/create")
	public String create(AccountForm accountForm) {
	
		return "create";

	}

	@PostMapping("/create")
	public String create(@Valid AccountForm accountForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(1);
			return "/create";
		}

		try {
			String username = accountForm.getName();
			System.out.println(2);
			String password = accountForm.getPassword();
			System.out.println(3);

//			springUserService.createUser(username, password);
			System.out.println(4);
			userService.saveUser(username, password);
			return "redirect:/";
		} catch (DuplicateKeyException e) {
			bindingResult.addError(new FieldError("user", "username", "すでに存在するユーザーです。"));
			return "login";

		}

	}
}
