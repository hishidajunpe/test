package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;
import com.example.demo.service.RegisterService;
import com.example.demo.service.SpringUserService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private SummaryRepository summaryReposiroty;

	@Autowired
	private SpringUserService springUserService;
	
	@Autowired
	private UserService userService;


	// ログイン画面へ遷移する
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// 新規登録画面へ遷移する
	@GetMapping("/create")
	public String create(User user,String name,String password) {
		return "create";

	}

	@PostMapping("/create")
	public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(1);
			return "/login";
		}

		try {
			String username = user.getName();
			System.out.println(2);
			String password = user.getPassword();
			System.out.println(3);

			springUserService.createUser(username, password);
			System.out.println(4);
			userService.saveUser(username, password);
			return "redirect:/";
		} catch (DuplicateKeyException e) {
			bindingResult.addError(new FieldError("user", "username", "すでに存在するユーザーです。"));
			return "login";

		}

	}
}
