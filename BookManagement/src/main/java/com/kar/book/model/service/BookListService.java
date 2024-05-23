package com.kar.book.model.service;

import java.util.List;

import com.kar.book.model.dto.BookList;
import com.kar.book.model.dto.PostsDTO;

public interface BookListService {

	/** 도서목록 조회하기
	 * @return bookList
	 */
	List<BookList> selectList();

	/** 도서 목록 추가
	 * @param bookTitle
	 * @param bookWriter
	 * @param bookPrice
	 * @return result
	 */
	int insertBook(String bookTitle, String bookWriter, int bookPrice);



	/** 책 목록 검색
	 * @param bookTitle
	 * @return bookList
	 */
	List<BookList> bookTitleSearch(String bookTitle);

	//이벤트 조회
	List<PostsDTO> findEvents();

}
