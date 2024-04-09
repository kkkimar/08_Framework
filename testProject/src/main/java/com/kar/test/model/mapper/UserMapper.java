package com.kar.test.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kar.test.model.dto.User;

@Mapper
public interface UserMapper {

	
	// 회원 조회
	List<User> userIdSearch(String userId);

}
