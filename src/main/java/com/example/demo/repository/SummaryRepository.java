package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.example.demo.model.Summary;
import com.example.demo.model.User;

// @Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer>{

	//	指定されたユーザーIDから〇月の収入データを取得
	List<Summary>findByStatusTrueAndUserAndYearAndMonth(User user,Integer year, Integer month);
	
	//	指定されたユーザーIDから〇月の支出のデータを取得
	List<Summary> findByStatusFalseAndUserAndYearAndMonth(User user,Integer year, Integer month);
}
