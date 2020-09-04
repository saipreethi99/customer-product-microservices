package com.dxctraining.customermgt.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.customermgt.customer.dao.ICustomerDao;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.exceptions.CustomerNotFoundException;
import com.dxctraining.customermgt.exceptions.InvalidArgumentException;

import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDao dao;

	@Override
	public Customer findCustomerById(int id) {
		Optional<Customer> optional=dao.findById(id);
		if(!optional.isPresent()) {
			throw new CustomerNotFoundException("Customer not found for id="+id);
		}
		Customer customer=optional.get();
		return customer;
	}

	@Override
	public Customer save(Customer customer) {
		validate(customer);
		customer=dao.save(customer);
		return customer;
	}
	
	private void validate(Customer customer) {
		if(customer==null) {
			throw new InvalidArgumentException("customer should not be null");
	}
	}
	@Override
	public List<Customer> findCustomerByName(String name) {
		List<Customer> list=dao.findCustomerByName(name);
		return list;
	}

	@Override
	public List<Customer> displayAllCustomers() {
		List<Customer>list=dao.findAll();
		return list;
	}
	
}