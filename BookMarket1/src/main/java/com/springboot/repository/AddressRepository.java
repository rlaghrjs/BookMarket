package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
