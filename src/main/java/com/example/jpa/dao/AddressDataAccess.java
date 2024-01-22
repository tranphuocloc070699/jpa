package com.example.jpa.dao;

import com.example.jpa.model.Address;
import com.example.jpa.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AddressDataAccess {
  private final AddressRepository repository;

  public Optional<Address> findById(Integer id) {
    return repository.findById(id);
  }

  public Address save(Address entity) {
    return repository.save(entity);
  }

  public Page<Address> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
