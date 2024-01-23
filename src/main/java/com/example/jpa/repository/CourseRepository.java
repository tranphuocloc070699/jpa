package com.example.jpa.repository;

import com.example.jpa.model.Address;
import com.example.jpa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
