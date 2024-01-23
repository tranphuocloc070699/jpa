package com.example.jpa.dao;

import com.example.jpa.model.User;
import com.example.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDataAccess {
  private final UserRepository repository;

  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }

  public User save(User entity) {
    return repository.save(entity);
  }

  public Page<User> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public void delete(Long id) {
     repository.deleteById(id);
  }
}
