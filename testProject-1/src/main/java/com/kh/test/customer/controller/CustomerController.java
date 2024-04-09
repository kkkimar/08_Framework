package com.kh.test.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.customer.model.dto.Customer;
import com.kh.test.customer.model.service.CustomerService;
import com.kh.test.customer.model.service.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("customer")
public class CustomerController {
	
	private final CustomerService service;
	
	
	@PostMapping("insertCustomer")
	public String insertCustomer(
			Customer customer,
			Model model
			) {
		
		
		int result = service.insertCustomer(customer);
		String path = null;
		String customerName = customer.getCustomerName();
		
		
		if(result > 0) {
			
			path = "/result";
			model.addAttribute("customerName", customerName);
		}
		
		return path;
	}
	
	
	

}
