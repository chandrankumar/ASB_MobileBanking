package com.asb.business.session.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asb.business.session.model.Customer;
import com.asb.business.session.repo.CustomerRepository;
import com.asb.business.session.service.SessionBusinessAdapterPublicService;

/**
 *
 * A Sessions Business controller
 */
@RestController
public class SessionsBusinessController implements SessionBusinessAdapterPublicService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

		customer = customerRepository.saveAndFlush(customer);

		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

		Customer cust = customerRepository.save(customer);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteCustomer(@PathVariable(name = "customer_id") String customerId) {

		customerRepository.delete(Integer.valueOf(customerId));
		return new ResponseEntity<>("Customer Offboarded successfully", HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<List<Customer>> retrieveCustomers() {

		List<Customer> customers = customerRepository.findAll();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

}
