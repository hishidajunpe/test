package com.example.demo.model.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Summary;

public class SummaryTest {
private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void testValidationSuccess() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(15);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(0);
	}
	
	@Test
	public void testValidationFailYearNull() {
		Summary summary = new Summary();
		summary.setMonth(5);
		summary.setDay(15);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
	}
	}
	
	@Test
	public void testValidationFailMonthNull() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setDay(15);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
	}
	}


	@Test
	public void testValidationFailMonthMin() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(0);
		summary.setDay(15);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Min.class);
	}
	}
	
	
	@Test
	public void testValidationFailMonthMax() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(20);
		summary.setDay(15);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Max.class);
	}
	}
	
	@Test
	public void testValidationFailDayNull() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
	}
	}
	
	@Test
	public void testValidationFailDayMin() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(0);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Min.class);
	}
	}
	
	@Test
	public void testValidationFailDayMax() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(50);
		summary.setGenre("食費");
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Max.class);
	}
	}
	
	@Test
	public void testValidationFailMoneyNull() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(15);
		summary.setGenre("食費");
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotNull.class);
	}
	}
	
	@Test
	public void testValidationFailGenreBlank() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(15);
		summary.setMoney(300);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(NotBlank.class);
	}
	}
	
	@Test
	public void testValidationFailGenreSize() {
		Summary summary = new Summary();
		summary.setYear(2023);
		summary.setMonth(5);
		summary.setDay(15);
		summary.setMoney(300);
		
		String a="";
		for(int i = 0; i<3; i++) {
			a += "1234567890";
		}
		
		summary.setGenre(a);
		
		Set<ConstraintViolation<Summary>> violations = validator.validate(summary);
		assertThat(violations.size()).isEqualTo(1);
		for(ConstraintViolation<Summary> violation : violations) {
			Object annotation = violation.getConstraintDescriptor().getAnnotation();
			assertThat(annotation).isInstanceOf(Size.class);
	}
	}
}
