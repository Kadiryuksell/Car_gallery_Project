package com.kadirkaganyuksel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kadirkaganyuksel.model.Gallerist;

@Repository
public interface GalleristRepository extends JpaRepository<Gallerist, Long>{

	Optional<Gallerist> findByAddressId(Long AddressId);
	
}
