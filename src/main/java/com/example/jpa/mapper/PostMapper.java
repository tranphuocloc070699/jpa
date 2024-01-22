package com.example.jpa.mapper;

import com.example.jpa.dto.PostDto;
import com.example.jpa.model.Post;

public class PostMapper {

  public static PostDto mapToDto(Post entity, PostDto dto) {

    dto.setTitle(entity.getTitle());
    dto.setContent(entity.getContent());
    return dto;
  }

  public static Post mapToEntity(Post entity,PostDto dto) {

    entity.setTitle(dto.getTitle());
    entity.setContent(dto.getContent());
    return entity;
  }
}