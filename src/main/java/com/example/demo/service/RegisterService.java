package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Summary;
import com.example.demo.repository.SummaryRepository;

@Service
public class RegisterService {

	@Autowired
	private SummaryRepository summaryRepository;

	//　登録
	public Summary createSummary(Integer month,Integer day,Integer money,String genre,Boolean status){
	Summary summary = new Summary();
	summary.setMonth(month);
	summary.setDay(day);
	summary.setMoney(money);
	summary.setGenre(genre);
	summary.setStatus(status);
	summaryRepository.save(summary);
	return summary;
	}

	//　編集
	void editSummary(Summary summary){
	summaryRepository.save(summary);
	}
}
