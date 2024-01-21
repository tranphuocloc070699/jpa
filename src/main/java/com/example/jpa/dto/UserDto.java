package com.example.jpa.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  @JsonProperty("first_name")
  private String firstName;
  private String lastName;
  private Integer age;
  private Boolean isAdult;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
