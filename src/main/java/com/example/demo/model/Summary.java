package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Summary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotEmpty(message = "登録する年を入力してください")
	@Min(1)
	@Max(12)
	private Integer year;
	
	@NotEmpty(message = "登録する月を入力してください")
	@Min(1)
	@Max(12)
	private Integer month;
	
	@NotEmpty(message = "登録する日を入力してください")
	@Min(1)
	@Max(31)
	private Integer day;
	
	@NotEmpty(message = "登録する金額を入力してください")
	@Size(max = 9,message = "10億円以内で登録してください")
	private Integer money;
	
	@NotBlank(message = "ジャンルを入力してください")
	@Size(max = 20,message = "20文字以内で入力してください")
	private String genre;
	
	//true:収入 false:支出
	private Boolean status;
	
	@ManyToOne
	private User user;
}
