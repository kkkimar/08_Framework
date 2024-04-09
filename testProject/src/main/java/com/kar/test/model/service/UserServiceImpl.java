package com.kar.test.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kar.test.model.dto.User;
import com.kar.test.model.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

	private final UserMapper mapper;
	
	
	// 회원조회
	@Override
	public List<User> userIdSearch(String userId) {
		
		
		return mapper.userIdSearch(userId);
	}
	
}
