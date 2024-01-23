package com.example.jpa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class User {
  @Id
  @GeneratedValue
//  @GeneratedValue(strategy = GenerationType.UUID)
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private Integer age;

  @Column
  private Boolean isAdult;

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


  @OneToMany(mappedBy = "user")
  private List<Post> posts;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @ManyToMany(mappedBy = "users")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<Course> courses;

}
