package com.asb.session.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asb.business.session.dto.CustomerDTO;
import com.asb.business.session.model.Customer;
import com.asb.business.session.service.SessionBusinessAdapterPublicService;
import com.asb.session.service.SessionMobileAdapterPublicService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * A SessionMobileAdapterController
 */
@RestController
public class SessionMobileAdapterController implements SessionMobileAdapterPublicService {

	private static final Logger logger = LoggerFactory.getLogger(SessionMobileAdapterController.class);
	
	@Autowired
	private SessionBusinessAdapterPublicService sessionBusiness;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public ResponseEntity<CustomerDTO> onboardCustomer(@RequestBody CustomerDTO customerDto) {

		Customer customer = objectMapper.convertValue(customerDto, Customer.class);
		customer.getAccounts().forEach(acc -> acc.setCustomer(customer));

		ResponseEntity<Customer> onboardCustomer = sessionBusiness.createCustomer(customer);

		logger.info("SessionMobileAdapterController -> onboardCustomer:: "+onboardCustomer.getBody().toString());
		return new ResponseEntity<>(objectMapper.convertValue(onboardCustomer.getBody(), CustomerDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerDTO> modifyCustomer(@RequestBody CustomerDTO customerDTO) {

		Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
		customer.getAccounts().forEach(acc -> acc.setCustomer(customer));

		ResponseEntity<Customer> updatedCustomer = sessionBusiness.updateCustomer(customer);
		logger.info("SessionMobileAdapterController -> modifyCustomer:: "+updatedCustomer.toString());
		return new ResponseEntity<>(objectMapper.convertValue(updatedCustomer.getBody(), CustomerDTO.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> offboardCustomer(@PathVariable(name = "customer_id") String customerId) {

		sessionBusiness.deleteCustomer(customerId);
		logger.info("SessionMobileAdapterController -> offboardCustomer:: "+customerId);
		return new ResponseEntity<>("Customer Offboarded successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CustomerDTO>> retrieveCustomers() {

		ResponseEntity<List<Customer>> customers = sessionBusiness.retrieveCustomers();
		List<CustomerDTO> customersDTO = customers.getBody().stream()
				.map(c -> objectMapper.convertValue(c, CustomerDTO.class)).collect(Collectors.toList());
		logger.info("SessionMobileAdapterController -> retrieveCustomers:: "+customersDTO.toString());
		return new ResponseEntity<List<CustomerDTO>>(customersDTO, HttpStatus.OK);
	}

}
