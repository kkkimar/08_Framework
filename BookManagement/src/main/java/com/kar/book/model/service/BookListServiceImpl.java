package com.kar.book.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kar.book.model.dto.BookList;
import com.kar.book.model.dto.PostsDTO;
import com.kar.book.model.mapper.BookListMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService {

	
	//DI 주입
	private final BookListMapper mapper;
	
	// 도서목록 조회하기
	@Override
	public List<BookList> selectList() {
		
		return mapper.selectList();
	}
	
	
	//도서 목록 추가
	@Override
	public int insertBook(String bookTitle, String bookWriter, int bookPrice) {
		
		BookList bookList = new BookList();
		
		bookList.setBookTitle(bookTitle);
		bookList.setBookWriter(bookWriter);
		bookList.setBookPrice(bookPrice);
		
		return mapper.insertBook(bookList);
	}
	
	// 도서 목록 검색
	@Override
	public List<BookList> bookTitleSearch(String bookTitle) {
		return mapper.bookTitleSearch(bookTitle);
	}
	
	//이벤트 조회 테스트
	@Override
	public List<PostsDTO> findEvents() {
		return mapper.findEvents();
	}
	
	
}
