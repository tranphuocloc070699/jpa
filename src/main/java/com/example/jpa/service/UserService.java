package com.example.jpa.service;

import com.example.jpa.dao.UserDataAccess;
import com.example.jpa.dto.UserDto;
import com.example.jpa.exceptions.AlreadyExistException;
import com.example.jpa.exceptions.ConflictException;
import com.example.jpa.exceptions.ResourceNotFoundException;
import com.example.jpa.mapper.UserMapper;
import com.example.jpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserDataAccess dataAccess;

  public User createEntity(UserDto dto) {
    User entity = User.builder().build();
    UserMapper.mapToEntity(entity, dto);

    return dataAccess.save(entity);
  }


  public Page<User> fetchEntities(int currentPage,int pageSize ,String sortBy, String sortDir) {
    Sort sort = Sort.by(sortBy);

    sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

    return dataAccess.findAll(pageable);
  }


  public User fetchEntity(Integer id) {
    return dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
  }


  public User updateEntity(UserDto dto,Integer id) {
    boolean isUpdated = false;
    User modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
    if (!modelExisted.getFirstName().equals(dto.getFirstName())) {
      modelExisted.setFirstName(dto.getFirstName());
      isUpdated=true;
    }
    if (!modelExisted.getLastName().equals(dto.getLastName())) {
      modelExisted.setLastName(dto.getLastName());
      isUpdated=true;
    }
    if (!modelExisted.getAge().equals(dto.getAge())) {
      modelExisted.setAge(dto.getAge());
      isUpdated=true;
    }
    if (!modelExisted.getIsAdult().equals(dto.getIsAdult())) {
      modelExisted.setIsAdult(dto.getIsAdult());
      isUpdated=true;
    }
    if(!isUpdated) throw new ConflictException("Property not changing");
    dataAccess.save(modelExisted);
    return modelExisted;
  }


  public User deleteEntity(Integer id) {
    User modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id.toString()));
    dataAccess.delete(id);
    return modelExisted;
  }
}
