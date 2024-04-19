package com.kh.test.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.test.board.model.dto.Board;
import com.kh.test.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("user")
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	
	@PostMapping("titleSearch")
	public String titleSearch(
			@RequestParam("inputTitle") String inputTitle,
			Model model
			) {
		
		List<Board> boardList = service.titleSearch(inputTitle);
		String path = null;
		
		model.addAttribute("boardList",boardList);
		
		if(boardList.isEmpty()) {
			path = "/searchFail";
		}else {
			path = "/searchSuccess";
		}
		
		return path;
	}
	
	
}
