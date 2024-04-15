package edu.kh.project.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	
	
	/** 게시판 종류 조회
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/** 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	int getListCount(int boardCode);

	
	/** 특정 게시판 지정된 페이지 목록 조회
	 * @param boardCode
	 * @param rowBounds
	 * @return boardList
	 */
	List<Board> selectBoardList(int boardCode, RowBounds rowBounds);

	/** 게시글 상세 조회
	 * @param map
	 * @return
	 */
	Board selectOne(Map<String, Integer> map);

	/** 좋아요 해제(DELETE)
	 * @param map
	 * @return result
	 */
	int deleteBoardLike(Map<String, Integer> map);

	/** 좋아요 체크(INSERT)
	 * @param map
	 * @return result
	 */
	int insertBoardLike(Map<String, Integer> map);

	/** 다시 해당 게시글의 좋아요 개수를 조회해서 반환
	 * @param integer
	 * @return count
	 */
	int selectLikeCount(int temp);
	
	

	/** 조회수 1증가
	 * @param boardNo
	 * @return
	 */
	int updateReadCount(int boardNo);
	

	/** 조회 수 조회
	 * @param boardNo
	 * @return
	 */
	int selectReadCoun(int boardNo);

}
