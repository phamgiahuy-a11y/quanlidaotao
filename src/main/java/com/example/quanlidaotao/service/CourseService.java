package com.example.quanlidaotao.service;

import com.example.quanlidaotao.dto.CourseDTO;
import com.example.quanlidaotao.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface CourseService {
    Page<Course> getAll(Pageable pageable, String keyword);
    List<Course> getAllList();                    // cho dropdown
    Course getById(UUID id);
    Course create(CourseDTO dto);
    Course update(UUID id, CourseDTO dto);
    void delete(UUID id);
}