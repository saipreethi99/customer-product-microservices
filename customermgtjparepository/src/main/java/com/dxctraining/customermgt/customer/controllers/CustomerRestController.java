package com.dxctraining.customermgt.customer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.customermgt.customer.dto.CreateCustomerRequest;
import com.dxctraining.customermgt.customer.dto.CustomerDto;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.service.ICustomerService;
import com.dxctraining.customermgt.customer.util.CustomerUtil;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	@Autowired
	private ICustomerService customerservice;

	@Autowired
	private CustomerUtil customerUtil;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto add(@RequestBody CreateCustomerRequest requestdata) {
		Customer customer = new Customer(requestdata.getName());
		customer = customerservice.save(customer);
		CustomerDto response = customerUtil.customerDto(customer);
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDto findCustomerById(@PathVariable("id") int id) {
		Customer customer = customerservice.findCustomerById(id);
		CustomerDto response = customerUtil.customerDto(customer);
		return response;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> fetchAllCustomers() {
		List<Customer> list = customerservice.displayAllCustomers();
		List<CustomerDto> response = new ArrayList<>();
		for (Customer customer : list) {
			CustomerDto dto = customerUtil.customerDto(customer);
			response.add(dto);
		}
		return response;
	}

	@GetMapping("/get/customer/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerDto> FindCustomerByName(@PathVariable("name") String name) {
		List<Customer> list = customerservice.findCustomerByName(name);
		List<CustomerDto> response = new ArrayList<>();
		for (Customer customer : list) {
			CustomerDto dto = customerUtil.customerDto(customer);
			response.add(dto);
		}
		return response;
	}

}
