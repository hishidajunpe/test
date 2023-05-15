package com.example.demo.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.UserController;
import com.example.demo.model.Account;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.service.RegisterService;
import com.example.demo.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private RegisterService registerService;
	
	@MockBean
    private UserDetailsManager userDetailsManager;
    

    @MockBean
    private Account account;

    @MockBean
    private static User user;
    
    private static List<Summary> summary;
    private static List<Summary> summary1;
    @BeforeEach
    public void setUp() {

    }
	
//	@Test
//	public void testIndexNotLogin() throws Exception{
//		mockMvc.perform(get("/"))
//		.andExpect(view().name("login"));
//	}
	
//	 @Test
//	    @WithMockUser
//	    public void testIndexHasNotPermission() throws Exception {
//	        mockMvc.perform(get("/"))
//	            .andExpect(status().isForbidden());
//	    }
	    
	 @Test
	    @WithMockUser(roles="USER")
	    public void testIndexHasPermission() throws Exception {  
	          mockMvc.perform(get("/"))
              .andExpect(status().isOk())
              .andExpect(view().name("index"));
	    }
	 
//	 @Test
//	    @WithMockUser(roles="USER")
//	 public void testSumIncome() {
//		    int year = 2022;
//	        int month = 5;
//	        int sumIncome = 100;
//	        int sumOutcome = 50;
////	        List<Summary> summary= new ArrayList<>();
//	
//	        List<Summary> summary1 = new ArrayList<>();
//
//	   
//	        mockMvc.perform(post("/")
//	                .param("year", String.valueOf(year))
//	                .param("month", String.valueOf(month))
//	                .sessionAttr("user", user))
//	                .andExpect(status().isOk())
//	                .andExpect(view().name("index"))
//	                .andExpect(model().attribute("year", year))
//	                .andExpect(model().attribute("month", month))
//	                .andExpect(model().attribute("sumIncome", sumIncome))
//	                .andExpect(model().attribute("sumOutcome", sumOutcome))
//	                .andExpect(model().attribute("tableIncome", summary))
//	                .andExpect(model().attribute("tableOutcome", summary1));
//	    }
	 }
	 
	


