/**
 * 
 */
package com.asb.session.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asb.business.session.dto.CustomerDTO;

/**
 * @author ChandranSrinivasan
 *
 */
@FeignClient("ASB-MOBILEBANKING-SERVICE-SESSION")
public interface SessionMobileAdapterThymeleafPublicService {

	// Thymleaf page
	@GetMapping(value = "/api/ui/customers")
	public String fetchCustomers(Model model);

	@GetMapping(value = "/api/ui/create/customer")
	String showCustomerForm(Model model);

	@PostMapping(value = "/api/ui/create/customer")
	public String submitCustomer(@ModelAttribute("customerDto") CustomerDTO customerDto);

	@GetMapping(value = "/api/offboard/{customer_id}")
	public String offboardCustomer(@PathVariable(name = "customer_id") String customerId);

}
