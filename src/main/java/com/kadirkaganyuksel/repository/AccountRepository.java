package com.kadirkaganyuksel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadirkaganyuksel.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
