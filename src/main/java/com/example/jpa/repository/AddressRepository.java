package com.example.jpa.repository;

import com.example.jpa.model.Address;
import com.example.jpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
