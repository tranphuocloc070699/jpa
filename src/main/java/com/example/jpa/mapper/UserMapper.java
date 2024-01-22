package com.example.jpa.mapper;

import com.example.jpa.dto.UserDto;
import com.example.jpa.model.User;

public class UserMapper {

  public static UserDto mapToDto(User entity,UserDto dto) {

     dto.setFirstName(entity.getFirstName());
     dto.setLastName(entity.getLastName());
     dto.setAge(entity.getAge());
     dto.setIsAdult(entity.getIsAdult());
   /*  dto.setCreatedAt(entity.getCreatedAt());
     dto.setUpdatedAt(entity.getUpdatedAt());*/
     return dto;
  }

  public static User mapToEntity(User entity,UserDto dto) {

    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setAge(dto.getAge());
    entity.setIsAdult(dto.getIsAdult());
    /*entity.setCreatedAt(dto.getCreatedAt());
    entity.setUpdatedAt(dto.getUpdatedAt());*/
    return entity;
  }
}
