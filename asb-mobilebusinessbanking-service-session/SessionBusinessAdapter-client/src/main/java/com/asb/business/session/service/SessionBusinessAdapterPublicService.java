package com.asb.business.session.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asb.business.session.model.Customer;

@FeignClient(name = "ASB-BUSINESSBANKING-SERVICE-SESSION")
public interface SessionBusinessAdapterPublicService {
	
	@RequestMapping(value = "/api/create/customer", method = RequestMethod.POST, produces = "application/JSON")
	ResponseEntity<Customer> createCustomer(@RequestBody Customer customer);
	
	@RequestMapping(value = "/api/update/customer", method = RequestMethod.PUT, produces = "application/JSON")
	ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer);
	
	@RequestMapping(value = "/api/delete/{customer_id}", method = RequestMethod.DELETE, produces = "application/JSON")
	ResponseEntity<String> deleteCustomer(@PathVariable(name = "customer_id") String customerId);

	@RequestMapping(value = "/api/customers", method = RequestMethod.GET, produces = "application/JSON")
	ResponseEntity<List<Customer>> retrieveCustomers();
	
}
