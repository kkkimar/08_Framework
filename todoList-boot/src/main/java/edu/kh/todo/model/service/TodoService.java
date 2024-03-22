package edu.kh.todo.model.service;

import java.util.Map;

public interface TodoService {

	/** 할일 목록 + 완료된 할 일 개수 조회
	 * @return map
	 */
	Map<String, Object> selectAll();

	int addTodo(String todoTitle, String todoContent);

}
