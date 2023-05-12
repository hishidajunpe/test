package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;

@Service
public class RegisterService {

	@Autowired
	private SummaryRepository summaryRepository;

	//	　支出・収入登録機能
	public void createSummary(Integer year,Integer month,Integer day,Integer money,String genre,Boolean status,User user){

		Summary summary1 = new Summary();
		summary1.setYear(year);
		summary1.setMonth(month);
		summary1.setDay(day);
		summary1.setMoney(money);
		summary1.setGenre(genre);
		summary1.setStatus(status);
		summary1.setUser(user);
		summaryRepository.save(summary1);
		//		summary.add(summary1);
		//		return summary1;
	}

	//	登録内容編集機能
	public void editSummary(Summary summary){
		summaryRepository.save(summary);
	}

	//	収入の合計表示 
	public int getSumIncome(User user,Integer year, Integer month){
		int totalIncome = 0;
		System.out.println(1);
		 List<Summary> summary1 =summaryRepository.findByStatusTrueAndUserAndYearAndMonth(user, year, month);
		for (int i = 0;i<summary1.size();i++){
			int income =summary1.get(i).getMoney();
			
			totalIncome += income;
//			if(summary.get(i).getStatus() == true) {
//						}
		}
		System.out.println(2);return totalIncome;
	}

	//	収入の合計表示 
	//	public int getSumIncome(@ModelAttribute List<Summary> summary,Integer year, Integer month){
	//		int totalIncome = 0;
	//		for (int i = 0;i<summary.size();i++){
	//			int income = summaryRepository.findByStatusTrueAndYearAndMonth(year, month).get(i).getMoney();
	//			if(summary.get(i).getStatus() == true) {
	//
	//				totalIncome += income;
	//			}
	//		}return totalIncome;
	//	}

	//	支出の合計表示 
	public int getSumOutcome(User user,Integer year, Integer month){
		int totalOutcome = 0;
		List<Summary> summary=summaryRepository.findByStatusFalseAndUserAndYearAndMonth(user,year, month);
		for (int i = 0;i<summary.size();i++){

			int outcome = summary.get(i).getMoney();
			
				totalOutcome += outcome;
	
		}
		return totalOutcome;
	}
	//	public int getSumOutcome(@ModelAttribute List<Summary> summary,Integer userId, Integer year, Integer month){
	//		int totalOutcome = 0;
	//		for (int i = 0;i<summary.size();i++){
	//			int outcome = summaryRepository.findByStatusFalseAndYearAndMonth(year, month).get(i).getMoney();
	//			if(summary.get(i).getStatus() == false) {
	//				totalOutcome += outcome;
	//			}
	//		}
	//		return totalOutcome;
	//	}

	//	収入の月間テーブルの表示
	public List<Summary> getIncomeMonth(User user,Integer year,Integer month){
	
		return summaryRepository.findByStatusTrueAndUserAndYearAndMonth(user, year, month);
	}
	//	public List<Summary> getIncomeMonth(@ModelAttribute List<Summary> summary,Integer year,Integer month){
	//		List<Summary> summary1 = summaryRepository.findByStatusTrueAndYearAndMonth(year, month);
	//		for (int i = 0;i<summary.size();i++){
	//			// status==trueのみだけ取り出したい
	//			if(summary1.get(i).getStatus()==true) {
	//				summary1.get(i).getYear();
	//				summary1.get(i).getMonth();
	//				summary1.get(i).getDay();
	//				summary1.get(i).getGenre();
	//				summary1.get(i).getMoney();
	//			}
	//		}
	//		return summary1;
	//	}
	//	支出の月間テーブルの表示
	public List<Summary> getOutcomeMonth(User user,Integer year,Integer month){
		
		return summaryRepository. findByStatusFalseAndUserAndYearAndMonth(user,year,month);
		
	}
//	public List<Summary> getOutcomeMonth(Integer year,Integer month){
//		List<Summary> summary1 = summaryRepository.findByStatusFalseAndYearAndMonth(year, month);
//		for (int i = 0;i<summary1.size();i++){
//			summary1.get(i).getYear();
//			summary1.get(i).getMonth();
//			summary1.get(i).getDay();
//			summary1.get(i).getGenre();
//			summary1.get(i).getMoney();
//		}return summary1;
//	}
	//	public List<Summary> getOutcomeMonth(@ModelAttribute List<Summary> summary,Integer year,Integer month){
	//		List<Summary> summary1 = summaryRepository.findByStatusFalseAndYearAndMonth(year, month);
	//		for (int i = 0;i<summary.size();i++){
	//			// status==trueのみだけ取り出したい
	//			if(summary1.get(i).getStatus()==false) {
	//				summary1.get(i).getYear();
	//				summary1.get(i).getMonth();
	//				summary1.get(i).getDay();
	//				summary1.get(i).getGenre();
	//				summary1.get(i).getMoney();
	//			}	
	//		}return summary1;
	//	}
}