package com.example.jpa.service;

import com.example.jpa.dao.AddressDataAccess;
import com.example.jpa.dao.UserDataAccess;
import com.example.jpa.dto.AddressDto;
import com.example.jpa.exceptions.ConflictException;
import com.example.jpa.exceptions.ResourceNotFoundException;
import com.example.jpa.mapper.AddressMapper;
import com.example.jpa.model.Address;
import com.example.jpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressDataAccess dataAccess;
  private final UserDataAccess userDataAccess;
  public Address createEntity(Integer id,AddressDto dto) {
    User user = userDataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
    Address entity = Address.builder().build();
    AddressMapper.mapToEntity(entity, dto);
    user.setAddress(entity);

//    entity.setUser(user);
    return dataAccess.save(entity);
  }


  public Page<Address> fetchEntities(int currentPage, int pageSize , String sortBy, String sortDir) {
    Sort sort = Sort.by(sortBy);

    sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

    return dataAccess.findAll(pageable);
  }

  public Address fetchEntity(Integer id) {
    return dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address","Id",id.toString()));
  }


  public Address updateEntity(AddressDto dto,Integer id) {
    boolean isUpdated = false;
    Address modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address","Id",id.toString()));
    if (!modelExisted.getLocation().equals(dto.getLocation())) {
      modelExisted.setLocation(dto.getLocation());
      isUpdated=true;
    }


    if(!isUpdated) throw new ConflictException("Property not changing");
    dataAccess.save(modelExisted);
    return modelExisted;
  }


  public Address deleteEntity(Integer id) {
    Address modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address","Id",id.toString()));
    dataAccess.delete(id);
    return modelExisted;
  }
}
