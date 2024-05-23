package com.kar.book.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kar.book.model.dto.BookList;
import com.kar.book.model.dto.PostsDTO;

@Mapper
public interface BookListMapper {

	/** 도서 목록 조회
	 * @return bookList
	 */
	List<BookList> selectList();

	
	/** 도서 등록
	 * @param bookList
	 * @return
	 */
	int insertBook(BookList bookList);


	
	/** 도서 목록 검색
	 * @param bookTitle
	 * @return
	 */
	List<BookList> bookTitleSearch(String bookTitle);


	//event 조회 테스트
	List<PostsDTO> findEvents();


	
	
	
	

}
