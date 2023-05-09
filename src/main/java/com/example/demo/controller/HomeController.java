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

@Controller
public class HomeController {

	@Autowired
	private SummaryRepository summaryReposiroty;

	@Autowired
	private SpringUserService springUserService;


	// ログイン画面へ遷移する
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// 新規登録画面へ遷移する
	@GetMapping("/create")
	public String create() {
		return "create";

	}

	@PostMapping("/create")
	public String create1(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/login";
		}

		try {
			String username = user.getName();
			String password = user.getPassword();

			springUserService.createUser(username, password);

			return "redirect:/index";
		} catch (DuplicateKeyException e) {
			bindingResult.addError(new FieldError("user", "username", "すでに存在するユーザーです。"));
			return "login";

		}

	}
}
