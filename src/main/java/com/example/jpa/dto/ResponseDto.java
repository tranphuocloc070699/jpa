package com.example.jpa.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
  private Date timestamp;
  private HttpStatus status;
  private Object data;
  private Object errors;
  private String message;
  private String path;
}