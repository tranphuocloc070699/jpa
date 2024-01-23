package com.example.jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Course {
  @Id
  @GeneratedValue
  private Integer id;

  @Column
  private String name;

  @Column
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate(){
    updatedAt = LocalDateTime.now();
  }

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinTable(name = "course_user", //Tạo ra một join Table tên là "address_person"
      joinColumns = @JoinColumn(name = "course_id"),  // TRong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (Address)
      inverseJoinColumns = @JoinColumn(name = "user_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
  )
  private List<User> users;


}
