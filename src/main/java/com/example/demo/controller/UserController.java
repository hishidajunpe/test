package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Summary;
import com.example.demo.model.User;
import com.example.demo.repository.SummaryRepository;
import com.example.demo.service.RegisterService;




@Controller
public class UserController {
	@ModelAttribute("user")
	public User currentUser(Account account) {
		return account.getUser();
	}
	
	@Autowired RegisterService registerService;
	@GetMapping("/")
	//	トップページに行き、収入の合計とそのテーブル、支出の合計とそのテーブルを表示したい。
	String index(){
		
		return "index";
	}
//////	収入の合計を表示
	//			int sumIncome = 0;
	//			sumIncome=registerService.getSumIncome(year, month);
	//			model.addAttribute("sumIncome", sumIncome);

	////	//	支出の合計を表示
	//			int sumOutcome = 0;
	//			sumOutcome=registerService.getSumOutcome(year, month);
	//			model.addAttribute("sumOutcome", sumOutcome);

	//	//	収入のテーブル情報をモデルに登録
	//		model.addAttribute("income",income);
	//
	//		Summary outcome = registerService.getOutcomeMonth
	//	//	支出のテーブル情報をモデルに登録
	//		model.addAttribute("outcome",outcome);
	
////	収入の合計を表示
	@PostMapping("/sumIncom")
	public String sumIncome(@RequestParam Integer year,Integer month,Model model) {
		int sumIncome = 0;
		sumIncome=registerService.getSumIncome(year, month);
		model.addAttribute("sumIncome", sumIncome);
		return "redirect:/index";
	}
////	支出の合計を表示
	@PostMapping("/sumOutcome")
	public String sumOut(@RequestParam Integer year,Integer month,Model model) {
		int sumOutcome = 0;
					sumOutcome=registerService.getSumOutcome(year, month);
					model.addAttribute("sumOutcome", sumOutcome);
		return "redirect:/index";
	}
	@PostMapping("/tableIncome")
	public String tableIncome(@RequestParam Integer year,Integer month,Model model) {
			List<Summary> summary = registerService.getIncomeMonth(year, month);
			model.addAttribute("tableIncome", summary);
		
		return "redirect:/index";
	}
	@PostMapping("/tableOutcome")
	public String tableOutcome(@RequestParam Integer year,Integer month,Model model) {
		List<Summary> summary = registerService.getOutcomeMonth(year, month);
		model.addAttribute("tableOutcome", summary);
	
		return "redirect:/index";
	}

	//	登録ページに移動する。
	@GetMapping("/register")
	String create(){
		return "register";
	}

	//	登録内容をDBへ反映する。失敗したら登録ページに戻る。成功したら再度登録ページに
	@PostMapping("/register")
	String createSummary(@ModelAttribute Summary summary,BindingResult  bindingResult,Integer month,Integer day,Integer money,String genre,Boolean status){
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		registerService.createSummary(month, day, money, genre, status);
//		summary.add(summary1);
		return "redirect:/register";
	}
	//	編集ページに移動する。
	@GetMapping("/edit")
	String edit(){
		return "edit";
	}

	//	編集内容をDBへ反映させる。失敗したら編集画面に戻る。成功したらトップ（index）にリダイレクトする。
	@PostMapping("/edit")
	String editSummary(@ModelAttribute Summary summary,BindingResult  bindingResult,Integer month,Integer day,Integer money,String genre,Boolean status ){
		if (bindingResult.hasErrors()) {
			return "/edit";
		}
		registerService.editSummary(summary);
		return "redirect:/";
	}
}
