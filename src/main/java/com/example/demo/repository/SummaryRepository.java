package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Summary;

@Repository
public interface SummaryRepository extends JpaRepository<Summary, Integer>{

	//指定されたユーザーIDからアカウント情報を取得
	List<summary>findByUserId(Integer userId);
	
	//指定されたユーザーIDから〇月の収入データを取得
	List<summary>findByUserIdAndStatusTrueAndMonthContains(Integer userId, Integer month);
	
	//指定されたユーザーIDから収入の全データを取得する
	List<summary>findByUserIdAndStatusTrue(Integer userId);
	
	//指定されたユーザーIDから〇月の支出のデータを取得
	List<summary> findByUserIdAndStatusFalseAndMonthContains(Integer userId, Integer month);
	
	//指定されたユーザーIDから支出の全データを取得する
	List<summary> findByUserIdAndStatusFalse(Integer userId);
	
}
