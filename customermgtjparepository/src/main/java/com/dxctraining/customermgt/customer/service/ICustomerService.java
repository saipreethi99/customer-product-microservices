package com.dxctraining.customermgt.customer.service;

import java.util.List;

import com.dxctraining.customermgt.customer.entities.Customer;

public interface ICustomerService {
	Customer findCustomerById(int id);

	Customer save(Customer customer);

	List<Customer> findCustomerByName(String name);

	List<Customer> displayAllCustomers();

}
