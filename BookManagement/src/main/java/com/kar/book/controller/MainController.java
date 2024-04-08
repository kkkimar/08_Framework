package com.kar.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Controller
public class MainController {

	//DI 주입하기
	private final BookListService service;
	
	@RequestMapping("/")
	public String mainPage() {
		
		// main페이지로 포워드
		return "/common/main";
	}
	
	
	
	
	
	
}
