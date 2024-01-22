package com.example.jpa.mapper;

import com.example.jpa.dto.AddressDto;
import com.example.jpa.model.Address;

public class AddressMapper {

  public static AddressDto mapToDto(Address entity, AddressDto dto) {

    dto.setLocation(entity.getLocation());
   
    return dto;
  }

  public static Address mapToEntity(Address entity,AddressDto dto) {

    entity.setLocation(dto.getLocation());
 
    return entity;
  }
}