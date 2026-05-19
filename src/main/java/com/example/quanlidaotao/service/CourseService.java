package com.example.quanlidaotao.service;

import com.example.quanlidaotao.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface CourseService {
    Page<Course> getAll(Pageable pageable, String keyword);
    Course getById(UUID id);
    Course create(Course course);
    Course update(UUID id, Course course);
    void delete(UUID id);
}