package com.example.jpa.model;

import java.time.LocalDateTime;

public class Post {
  private Integer id;
  private String title;
  private String content;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
