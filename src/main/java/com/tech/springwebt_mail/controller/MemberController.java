package com.tech.springwebt_mail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.springwebt_mail.command.EmailCheckCommand;
import com.tech.springwebt_mail.command.JoinCommand;
import com.tech.springwebt_mail.command.SCommand;
import com.tech.springwebt_mail.command.VCommand;



@Controller
public class MemberController {
	SCommand scommand;
	VCommand vcommand;
	
	@RequestMapping("/joinform")
	public String joinView(Model model) {
		return "joinform";
	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request, Model model) {
		System.out.println("=============join==============");
		model.addAttribute("request",request);
		scommand=new JoinCommand();
		String str=scommand.execute(model);
		if(str.equals("iddupl")) {
			model.addAttribute("idDupl","이미 가입된 아이디입니다");
			return "redirect:/";
		}
		if(str.equals("nndupl")) {
			model.addAttribute("nnDupl","사용할 수 없는 닉네임입니다");
			return "redirect:/";
		}
		return str;
	}
	
	@RequestMapping("/emailCheck")
	public String emailCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		scommand=new EmailCheckCommand();
		String str=scommand.execute(model);
		
		return str;
	}
	
	

	
	
}
