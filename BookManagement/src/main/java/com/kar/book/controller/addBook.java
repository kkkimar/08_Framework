package com.kar.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;

@RequestMapping("addBook")
@Controller
@RequiredArgsConstructor
public class addBook {

	private final BookListService service;
	
	@PostMapping("insertBook")
	public String insertBook(
			@RequestParam("bookTitle") String bookTitle,
			@RequestParam("bookWriter") String bookWriter,
			@RequestParam("bookPrice") int bookPrice,
			RedirectAttributes ra
			) {
		
		int result = service.insertBook(bookTitle,bookWriter,bookPrice);
		
		String message = null;
		
		if(result>0) {
			message = "등록 성공";
		}else {
			message = "등록 실패";
		}
		
		ra.addFlashAttribute("message",message);
		return "redirect:/";
		
	}
	
	
	
	
	
}
