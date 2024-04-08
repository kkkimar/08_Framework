package com.kar.book.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kar.book.model.dto.BookList;

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

}
