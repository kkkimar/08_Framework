package com.kh.test.customer.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.test.customer.model.dto.Customer;
import com.kh.test.customer.model.mapper.CustomerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerMapper mapper;

	
	// 고객 추가
	@Override
	public int insertCustomer(Customer customer) {
		return mapper.insertCustomer(customer);
	}
	
	

	

}
