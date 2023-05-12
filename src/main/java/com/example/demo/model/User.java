package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

import lombok.Data;

@Entity
//@Table(name="Users")
@Data
public class User {
//
////	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	
//	@NotBlank(message = "名前を入力してください")
//	@Size(max = 50,message = "名前は50文字以内で入力してください")
	@Id
	@Column(length = 80)
	private String name;
	
//	 private Boolean active;
	
	@OneToMany(mappedBy="user")
	private List<Summary> summary;
}
