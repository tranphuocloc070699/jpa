package com.example.jpa.mapper;

import com.example.jpa.dto.CourseDto;
import com.example.jpa.model.Course;

public class CourseMapper {

  public static CourseDto mapToDto(Course entity, CourseDto dto) {
    dto.setName(entity.getName());
    return dto;
  }

  public static Course mapToEntity(Course entity,CourseDto dto) {
    entity.setName(dto.getName());
    return entity;
  }
}