package com.kar.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kar.book.model.dto.BookList;
import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("bookSearch")
@Controller
@RequiredArgsConstructor
public class SearchBook {

	private final BookListService service;
	
	/** 책 목록 검색
	 * @return bookList
	 */
	@ResponseBody
	@PostMapping("bookTitleSearch")
	public List<BookList> bookTitleSearch(
			@RequestBody BookList bookTitle
			) {
		
		List<BookList> bookList = service.bookTitleSearch(bookTitle.getBookTitle());
		
		
		return bookList;
		
		
	}
	
	
	
}
