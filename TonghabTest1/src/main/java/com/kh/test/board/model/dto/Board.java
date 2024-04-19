package com.kh.test.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int boardNo;
	private String boardTitle ;
	private String boardContent;
	private String boardDate;
	private int boardReadCount;  
	private int userNo;
	private String userId;
	
}
