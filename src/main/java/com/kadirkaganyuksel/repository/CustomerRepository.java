package com.kadirkaganyuksel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadirkaganyuksel.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
