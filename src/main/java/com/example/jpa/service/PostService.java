package com.example.jpa.service;

import com.example.jpa.dao.PostDataAccess;
import com.example.jpa.dao.UserDataAccess;
import com.example.jpa.dto.PostDto;
import com.example.jpa.exceptions.ConflictException;
import com.example.jpa.exceptions.ResourceNotFoundException;
import com.example.jpa.mapper.PostMapper;
import com.example.jpa.model.Post;
import com.example.jpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostDataAccess dataAccess;
  private final UserDataAccess userDataAccess;
  public Post createEntity(Integer id,PostDto dto) {
    User user = userDataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id.toString()));
    Post entity = Post.builder().build();
    PostMapper.mapToEntity(entity, dto);
    entity.setUser(user);

    return dataAccess.save(entity);
  }


  public Page<Post> fetchEntities(int currentPage, int pageSize , String sortBy, String sortDir) {
    Sort sort = Sort.by(sortBy);

    sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

    Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

    return dataAccess.findAll(pageable);
  }

  public Post fetchEntity(Integer id) {
    return dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id.toString()));
  }


  public Post updateEntity(PostDto dto,Integer id) {
    boolean isUpdated = false;
    Post modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id.toString()));
    if (!modelExisted.getTitle().equals(dto.getTitle())) {
      modelExisted.setTitle(dto.getTitle());
      isUpdated=true;
    }
    if (!modelExisted.getContent().equals(dto.getContent())) {
      modelExisted.setContent(dto.getContent());
      isUpdated=true;
    }

    if(!isUpdated) throw new ConflictException("Property not changing");
    dataAccess.save(modelExisted);
    return modelExisted;
  }


  public Post deleteEntity(Integer id) {
    Post modelExisted = dataAccess.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id.toString()));
    dataAccess.delete(id);
    return modelExisted;
  }
}
