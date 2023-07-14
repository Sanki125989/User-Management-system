package com.user.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.user.management.model.Address;


public interface AddressRepository extends JpaRepository<Address, Long>{

}
