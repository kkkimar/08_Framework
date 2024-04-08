package com.kar.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kar.book.model.dto.BookList;
import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;

@RequestMapping("book")
@Controller
@RequiredArgsConstructor
public class AllBookList {

	public final BookListService service;
	
	
	/** 도서 목록 조회하기
	 * @return bookList
	 */
	@ResponseBody
	@GetMapping("bookList")
	public List<BookList> selectList(){
		
		//전체 목록 조회해서 가져오기
		List<BookList> bookList = service.selectList();
		
		return bookList;
	}
	
	@GetMapping("addBook")
	public String addBook() {
		
		return "addBook/addBookPage";
		
	}
	
	@GetMapping("bookSearch")
	public String bookSearch() {
		return "bookList/searchBook";
	}
	
	
	
}
