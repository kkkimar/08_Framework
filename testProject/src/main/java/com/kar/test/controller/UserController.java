package com.kar.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kar.test.model.dto.User;
import com.kar.test.model.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class UserController {
	
	private final UserService service;
	
	
	
	/** 회원 조회
	 * @param userId
	 * @return
	 */
	@PostMapping("user/userIdSearch")
	public String userIdSearch(
			@RequestParam("userId") String userId,
			Model model) {
		
		List<User> userList = service.userIdSearch(userId);
		
		model.addAttribute("userList",userList);
		
		String path = null;
		
		if(userList.isEmpty()) {
			
			path = "/searchFail";
			
		} else {
			path = "/searchSuccess";
		}
		
		return path;
		
		
	}
	

}
