package com.asb.business.session.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ChandranSrinivasan
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO{

	private Integer customerId;
	
	private String fullName;
	
	private Integer age;
	
	private String gender;
	
	private String address;
	
	private String occupation;
	
	private List<AccountsDTO> accounts;
	
}
