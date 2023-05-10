package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {

	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Id
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50,message = "名前は50文字以内で入力してください")
	@Column(name = "USERNAME")
	private String name;
	
	@OneToMany(mappedBy="user")
	private List<Summary> summary;
}
