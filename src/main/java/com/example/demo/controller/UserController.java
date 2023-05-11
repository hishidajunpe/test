package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PostMapping("/sumAndTable")
	public String sumIncome(@RequestParam Integer year,Integer month,Model model) {
		int sumIncome = 0;
		sumIncome=registerService.getSumIncome(year, month);
		model.addAttribute("sumIncome", sumIncome);

		int sumOutcome = 0;
					sumOutcome=registerService.getSumOutcome(year, month);
					model.addAttribute("sumOutcome", sumOutcome);
	
			List<Summary> summary = registerService.getIncomeMonth(year, month);
			model.addAttribute("tableIncome", summary);
		
		List<Summary> summary1 = registerService.getOutcomeMonth(year, month);
		model.addAttribute("tableOutcome", summary1);
	
		return "redirect:/index";
	}
	
//	@PostMapping("/sumIncom")
//	public String sumIncome(@RequestParam Integer year,Integer month,Model model) {
//		int sumIncome = 0;
//		sumIncome=registerService.getSumIncome(year, month);
//		model.addAttribute("sumIncome", sumIncome);
//		return "redirect:/index";
//	}
//////	支出の合計を表示
//	@PostMapping("/sumOutcome")
//	public String sumOut(@RequestParam Integer year,Integer month,Model model) {
//		int sumOutcome = 0;
//					sumOutcome=registerService.getSumOutcome(year, month);
//					model.addAttribute("sumOutcome", sumOutcome);
//		return "redirect:/index";
//	}
	
	

	//	登録ページに移動する。
	@GetMapping("/register")
	String create(Summary summary){
		return "register";
	}

	//	登録内容をDBへ反映する。失敗したら登録ページに戻る。成功したら再度登録ページに
	@PostMapping("/register")
	String createSummary(@ModelAttribute Summary summary,BindingResult  bindingResult,Integer year,User user,Integer month,Integer day,Integer money,String genre,boolean status){
		if (bindingResult.hasErrors()) {
			return "/register";
		}
		registerService.createSummary(year,month, day, money, genre, status,user);
//		summary.add(summary1);
		return "redirect:/register";
	}
	//	収入・支出の登録内容編集ページに移動する。
	//	このユーザーの登録内容を編集する。
	@GetMapping("/{id}/edit")
	String edit(@PathVariable("id") Summary summary,Model model){
		//	詰めなおす意味
		
		model.addAttribute("summary", summary);
		return "edit";
	}

	//	編集内容をDBへ反映させる。失敗したら編集画面に戻る。成功したらトップ（index）にリダイレクトする。
	@PostMapping("/{id}/edit")
	String editSummary(@Valid Summary summary,BindingResult  bindingResult ){
		if (bindingResult.hasErrors()) {
			return "/edit";
		}
		registerService.editSummary(summary);
		return "redirect:/";
	}
}
