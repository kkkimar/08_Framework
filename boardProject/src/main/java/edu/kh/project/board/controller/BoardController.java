package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService service;
	
	
	/** 게시글 목록 조회
	 * @param boardCode : 게시판 종류 구분
	 * @param cp : 현재 조회한 요청 페이지 (없으면 1)
	 * @return
	 * 
	 * - /board/000
	 *   /board 이하 1레벨 자리에 숫자로된 요청 주소가 작성되어 있을 때만 동작
	 * 		-> 정규 표현식 이용
	 * 
	 * [0-9] : 한 칸에 0~9사이 숫자 입력 가능
	 * + : 하나 이상
	 * 
	 * [0-9]+ : 모든 숫자
	 * 
	 * @PathVariable 주소를 변수에 담아서 사용 가능하게 함 *** 최근 많이 사용 ***
	 * (예전엔 쿼리스트링을 많이 사용 -> 최근은 pathvariable 사용 가능하면 pathvariable 사용!
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(
			@PathVariable("boardCode") int boardCode,
			@RequestParam (value="cp", 
						   required=false, 
						   defaultValue="1") int cp,
			Model model
			) {
		
//		log.debug("boardCode: " + boardCode);
		
		// 조회 서비스 호출 후 결과 반환
		Map<String, Object> map = service.selectBoardList(boardCode,cp);
		
		model.addAttribute("pagination",map.get("pagination"));
		model.addAttribute("boardList",map.get("boardList"));
		
		return "board/boardList";//boardList.html로 forward
	}
	
	
}
