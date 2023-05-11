package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@ControllerAdvice
public class CommonController {
	@Autowired UserService userService;
	
	@ModelAttribute("user")
    public User currentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)auth.getPrincipal();
            return userService.find(userDetails.getUsername());
        } else {
            return null;
        }
    }
	
	
	

}
