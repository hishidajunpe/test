package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AccountForm {
	
	  @NotBlank(message = "名前を入力してください")
	    private String name;
	    
	    @NotBlank(message = "パスワードを入力してください")
	    @Size(min = 8, message = "パスワードは8文字以上で入力してください")
	    private String password;

	  
}
