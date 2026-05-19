package com.example.quanlidaotao.service.impl;

import com.example.quanlidaotao.dto.CourseDTO;
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
import java.util.List;
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
    public List<Course> getAllList() {
        return repository.findAll();
    }

    @Override
    public Course getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy môn học với id: " + id));
    }

    @Override
    @Transactional
    public Course create(CourseDTO dto) {
        if (repository.findByCode(dto.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã môn học đã tồn tại");
        }

        Course course = Course.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .nameEn(dto.getNameEn())
                .credits(dto.getCredits())
                .courseType(dto.getCourseType())
                .theoryHours(dto.getTheoryHours())
                .practiceHours(dto.getPracticeHours())
                .selfStudyHours(dto.getSelfStudyHours())
                .description(dto.getDescription())
                .departmentId(dto.getDepartmentId())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();

        return repository.save(course);
    }

    @Override
    @Transactional
    public Course update(UUID id, CourseDTO dto) {
        Course existing = getById(id);
        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setNameEn(dto.getNameEn());
        existing.setCredits(dto.getCredits());
        existing.setCourseType(dto.getCourseType());
        existing.setTheoryHours(dto.getTheoryHours());
        existing.setPracticeHours(dto.getPracticeHours());
        existing.setSelfStudyHours(dto.getSelfStudyHours());
        existing.setDescription(dto.getDescription());
        existing.setDepartmentId(dto.getDepartmentId());
        existing.setUpdatedAt(LocalDateTime.now());

        return repository.save(existing);
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