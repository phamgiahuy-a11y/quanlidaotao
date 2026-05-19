package com.example.quanlidaotao.service.impl;

import com.example.quanlidaotao.entity.Course;
import com.example.quanlidaotao.exception.ResourceNotFoundException;
import com.example.quanlidaotao.repository.CourseRepository;
import com.example.quanlidaotao.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Override
    public Page<Course> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return repository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword.trim(), keyword.trim(), pageable);
        }
        return repository.findAll(pageable);
    }

    @Override
    public Course getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy môn học với id: " + id));
    }

    @Override
    @Transactional
    public Course create(Course course) {
        if (repository.findByCode(course.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã môn học đã tồn tại");
        }
        course.setCreatedAt(LocalDateTime.now());
        course.setIsActive(true);
        return repository.save(course);
    }

    @Override
    @Transactional
    public Course update(UUID id, Course course) {
        Course existing = getById(id);
        course.setId(id);
        course.setCreatedAt(existing.getCreatedAt());
        course.setUpdatedAt(LocalDateTime.now());
        return repository.save(course);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Course course = getById(id);
        course.setDeletedAt(LocalDateTime.now());
        course.setIsActive(false);
        repository.save(course);
    }
}