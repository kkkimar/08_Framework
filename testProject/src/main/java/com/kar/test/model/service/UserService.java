package com.kar.test.model.service;

import java.util.List;

import com.kar.test.model.dto.User;

public interface UserService {

	/** 회원 조회
	 * @param userId
	 * @return userList
	 */
	List<User> userIdSearch(String userId);

}
