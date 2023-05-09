package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50,message = "名前は50文字以内で入力してください")
	private String name;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8,max = 50,message = "パスワードは8文字以上50文字以内で入力してください")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Summary> summary;
}
