/**
 * 
 */
package com.asb.session.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asb.business.session.dto.CustomerDTO;

/**
 * @author ChandranSrinivasan
 *
 */
@FeignClient("ASB-MOBILEBANKING-SERVICE-SESSION")
public interface SessionMobileAdapterPublicService {

	@RequestMapping(value = "/api/onboard/customer", method = RequestMethod.POST, produces = "application/JSON")
	ResponseEntity<CustomerDTO> onboardCustomer(@RequestBody CustomerDTO customer);
	
	@RequestMapping(value = "/api/modify/customer", method = RequestMethod.PUT, produces = "application/JSON")
	ResponseEntity<CustomerDTO> modifyCustomer(@RequestBody CustomerDTO customer);
	
	@RequestMapping(value = "/api/offboard/{customer_id}", method = RequestMethod.DELETE, produces = "application/JSON")
	ResponseEntity<String> offboardCustomer(@PathVariable(name = "customer_id") String customerId);

	@RequestMapping(value = "/api/customers", method = RequestMethod.GET, produces = "application/JSON")
	ResponseEntity<List<CustomerDTO>> retrieveCustomers();

	@GetMapping(value = "/api/aws/")
	public String testAPI();
}
