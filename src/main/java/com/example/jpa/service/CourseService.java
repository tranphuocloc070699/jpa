package com.example.jpa.service;

import com.example.jpa.dao.CourseDataAccess;
import com.example.jpa.dao.UserDataAccess;
import com.example.jpa.dto.CourseDto;
import com.example.jpa.exceptions.ConflictException;
import com.example.jpa.exceptions.ResourceNotFoundException;
import com.example.jpa.mapper.CourseMapper;
import com.example.jpa.model.Course;
import com.example.jpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseDataAccess dataAccess;
  private final UserDataAccess userDataAccess;
  public Course createEntity(Integer id,CourseDto dto) {
    Course entity = Course.builder().build();
    CourseMapper.mapToEntity(entity, dto);
    return dataAccess.save(entity);
  }


  public Page<Course> fetchEntities(int currentPage, int pageSize , String sortBy, String sortDir) {
    Sort sort = Sort.by(sortBy);

    sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

    return dataAccess.findAll(pageable);
  }

  public Course fetchEntity(Integer id) {
    return dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",id.toString()));
  }


  public Course updateEntity(CourseDto dto,Integer id) {
    boolean isUpdated = false;
    Course modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",id.toString()));
    if (!modelExisted.getName().equals(dto.getName())) {
      modelExisted.setName(dto.getName());
      isUpdated=true;
    }
    if(!isUpdated) throw new ConflictException("Property not changing");
    dataAccess.save(modelExisted);
    return modelExisted;
  }

  public Course addUser(Integer id,Integer userId) {
    Course modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",id.toString()));
    User userExisted = userDataAccess.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
    modelExisted.getUsers().add(userExisted);
    modelExisted.setUsers(modelExisted.getUsers());
    dataAccess.save(modelExisted);
    return modelExisted;
  }

  public Course removeUser(Integer id,Integer userId) {
    Course modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",id.toString()));
    User userExisted = userDataAccess.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
    modelExisted.getUsers().remove(userExisted);
    modelExisted.setUsers(modelExisted.getUsers());

    return modelExisted;
  }


  public Course deleteEntity(Integer id) {
    Course modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course","Id",id.toString()));
    dataAccess.delete(id);
    return modelExisted;
  }
}
