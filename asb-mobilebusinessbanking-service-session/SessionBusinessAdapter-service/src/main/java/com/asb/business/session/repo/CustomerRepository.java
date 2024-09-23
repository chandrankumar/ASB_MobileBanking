package com.asb.business.session.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asb.business.session.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
