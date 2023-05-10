package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.Summary;
import com.example.demo.repository.SummaryRepository;

@Service
public class RegisterService {

	@Autowired
	private SummaryRepository summaryRepository;

	//	　支出・収入登録機能
	public Summary createSummary(@ModelAttribute List<Summary> summary,Integer month,Integer day,Integer money,String genre,Boolean status){
		Summary summary1 = new Summary();
		summary1.setMonth(month);
		summary1.setDay(day);
		summary1.setMoney(money);
		summary1.setGenre(genre);
		summary1.setStatus(status);
		summaryRepository.save(summary1);
		summary.add(summary1);
		return summary1;
	}

	//	登録内容編集機能
	public void editSummary(Summary summary){
		summaryRepository.save(summary);
	}

	//	収入の合計表示 
	public int getSumIncome(@ModelAttribute List<Summary> summary,Integer year, Integer month){
		int totalIncome = 0;
		for (int i = 0;i<summary.size();i++){
			int income = summaryRepository.findByStatusTrueAndYearContainsAndMonthContains(year, month).get(i).getMoney();
			if(summary.get(i).getStatus() == true) {

				totalIncome += income;
			}
		}return totalIncome;
	}

	//	支出の合計表示 
	public int getSumOutcome(@ModelAttribute List<Summary> summary,Integer userId, Integer year, Integer month){
		int totalOutcome = 0;
		for (int i = 0;i<summary.size();i++){
			int outcome = summaryRepository.findByStatusFalseAndYearContainsAndMonthContains(year, month).get(i).getMoney();
			if(summary.get(i).getStatus() == false) {
				totalOutcome += outcome;
			}
		}
		return totalOutcome;
	}

	//	収入の月間テーブルの表示
	public List<Summary> getIncomeMonth(@ModelAttribute List<Summary> summary,Integer year,Integer month){
		List<Summary> summary1 = summaryRepository.findByStatusTrueAndYearContainsAndMonthContains(year, month);
		for (int i = 0;i<summary.size();i++){
			// status==trueのみだけ取り出したい
			if(summary1.get(i).getStatus()==true) {
				summary1.get(i).getYear();
				summary1.get(i).getMonth();
				summary1.get(i).getDay();
				summary1.get(i).getGenre();
				summary1.get(i).getMoney();
			}
		}
		return summary1;
	}
	//	支出の月間テーブルの表示

	public List<Summary> getOutcomeMonth(@ModelAttribute List<Summary> summary,Integer year,Integer month){
		List<Summary> summary1 = summaryRepository.findByStatusFalseAndYearContainsAndMonthContains(year, month);
		for (int i = 0;i<summary.size();i++){
			// status==trueのみだけ取り出したい
			if(summary1.get(i).getStatus()==false) {
				summary1.get(i).getYear();
				summary1.get(i).getMonth();
				summary1.get(i).getDay();
				summary1.get(i).getGenre();
				summary1.get(i).getMoney();
			}	
		}return summary1;
	}
}