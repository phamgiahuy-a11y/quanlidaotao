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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    @Override
    public Page<Course> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return repository.searchActive(keyword.trim(), pageable);
        }
        return repository.findByIsActiveTrue(pageable);
    }

    @Override
    public List<Course> getAllList() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Course getById(UUID id) {
        return repository.findById(id)
                .filter(Course::getIsActive)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy môn học"));
    }

    @Override
    @Transactional
    public Course create(CourseDTO dto) {
        Optional<Course> existing = repository.findByCode(dto.getCode());
        Course course;

        if (existing.isPresent()) {
            course = existing.get();
            if (course.getIsActive()) {
                throw new RuntimeException("Mã môn học đã tồn tại và đang hoạt động!");
            }
            course.setIsActive(true); // Khôi phục môn học bị xóa mềm
        } else {
            course = new Course();
            course.setCreatedAt(LocalDateTime.now());
        }

        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setNameEn(dto.getNameEn());
        course.setCredits(dto.getCredits());
        course.setCourseType(dto.getCourseType());
        course.setTheoryHours(dto.getTheoryHours());
        course.setPracticeHours(dto.getPracticeHours());
        course.setSelfStudyHours(dto.getSelfStudyHours());
        course.setDescription(dto.getDescription());
        course.setDepartmentId(dto.getDepartmentId());
        course.setIsActive(true);
        
        return repository.save(course);
    }

    @Override
    @Transactional
    public Course update(UUID id, CourseDTO dto) {
        Course course = getById(id);
        course.setCode(dto.getCode());
        course.setName(dto.getName());
        course.setNameEn(dto.getNameEn());
        course.setCredits(dto.getCredits());
        course.setCourseType(dto.getCourseType());
        course.setTheoryHours(dto.getTheoryHours());
        course.setPracticeHours(dto.getPracticeHours());
        course.setSelfStudyHours(dto.getSelfStudyHours());
        course.setDescription(dto.getDescription());
        course.setDepartmentId(dto.getDepartmentId());
        course.setUpdatedAt(LocalDateTime.now());
        return repository.save(course);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Course course = getById(id);
        course.setIsActive(false); 
        // Bỏ deletedAt vì Entity Course không có
        repository.save(course);
    }
}