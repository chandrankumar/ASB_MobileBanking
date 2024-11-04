package com.asb.session.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.asb.business.session.dto.CustomerDTO;
import com.asb.business.session.model.Customer;
import com.asb.business.session.service.SessionBusinessAdapterPublicService;
import com.asb.session.service.SessionMobileAdapterThymeleafPublicService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SessionMobileAdapterThymeleafController implements SessionMobileAdapterThymeleafPublicService {

	private static final Logger logger = LoggerFactory.getLogger(SessionMobileAdapterController.class);

	@Autowired
	private SessionBusinessAdapterPublicService sessionBusiness;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String fetchCustomers(Model model) {

		ResponseEntity<List<Customer>> customers = sessionBusiness.retrieveCustomers();
		List<CustomerDTO> customersDTO = customers.getBody().stream()
				.map(c -> objectMapper.convertValue(c, CustomerDTO.class)).collect(Collectors.toList());
		logger.info("SessionMobileAdapterController -> retrieveCustomers:: " + customersDTO.toString());
		model.addAttribute("customers", customersDTO);
		return "dashboard";
	}

	@Override
	public String showCustomerForm(Model model) {
		model.addAttribute("customerDto", new CustomerDTO());
		return "create";
	}

	@Override
	public String submitCustomer(CustomerDTO customerDto) {

		Customer customer = objectMapper.convertValue(customerDto, Customer.class);
		customer.getAccounts().forEach(acc -> acc.setCustomer(customer));

		ResponseEntity<Customer> onboardCustomer = sessionBusiness.createCustomer(customer);

		logger.info("SessionMobileAdapterController -> onboardCustomer:: " + onboardCustomer.getBody().getCustomerId());

		return "redirect:/api/ui/customers";
	}


	@Override
	public String offboardCustomer(@PathVariable(name = "customer_id") String customerId) {
		sessionBusiness.deleteCustomer(customerId);
		logger.info("SessionMobileAdapterController -> offboardCustomer:: " + customerId);
		return "redirect:/api/ui/customers";
	}

}
