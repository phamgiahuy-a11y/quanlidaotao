package com.example.quanlidaotao.service.impl;

import com.example.quanlidaotao.dto.TrainingProgramCourseDTO;
import com.example.quanlidaotao.entity.TrainingProgramCourse;
import com.example.quanlidaotao.exception.ResourceNotFoundException;
import com.example.quanlidaotao.repository.TrainingProgramCourseRepository;
import com.example.quanlidaotao.service.TrainingProgramCourseService;
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
public class TrainingProgramCourseServiceImpl implements TrainingProgramCourseService {

    private final TrainingProgramCourseRepository repository;

    @Override
    public Page<TrainingProgramCourse> getAll(Pageable pageable) {
        return repository.findByIsActiveTrue(pageable);
    }

    @Override
    public List<TrainingProgramCourse> getAllList() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public List<TrainingProgramCourse> getByTrainingProgramId(UUID trainingProgramId) {
        return repository.findByTrainingProgramIdAndIsActiveTrueOrderBySortOrderAsc(trainingProgramId);
    }

    @Override
    public TrainingProgramCourse getById(UUID id) {
        return repository.findById(id)
                .filter(TrainingProgramCourse::getIsActive)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy học phần!"));
    }

    @Override
    @Transactional
    public TrainingProgramCourse create(TrainingProgramCourseDTO dto) {
        TrainingProgramCourse tpc = new TrainingProgramCourse();
        
        tpc.setTrainingProgramId(dto.getTrainingProgramId());
        tpc.setCourseId(dto.getCourseId());
        tpc.setCourseCode(dto.getCourseCode());
        tpc.setCourseName(dto.getCourseName());
        tpc.setSemesterId(dto.getSemesterId());
        tpc.setSemesterCode(dto.getSemesterCode());
        tpc.setAcademicYear(dto.getAcademicYear());
        tpc.setIsRequired(dto.getIsRequired());
        tpc.setGroupCode(dto.getGroupCode());
        tpc.setCredits(dto.getCredits());
        tpc.setSortOrder(dto.getSortOrder());
        tpc.setPrerequisiteCourseId(dto.getPrerequisiteCourseId());
        tpc.setNote(dto.getNote());
        tpc.setStatus(dto.getStatus());
        tpc.setCreatedAt(LocalDateTime.now());
        tpc.setIsActive(true);

        return repository.save(tpc);
    }

    @Override
    @Transactional
    public TrainingProgramCourse update(UUID id, TrainingProgramCourseDTO dto) {
        TrainingProgramCourse tpc = getById(id);
        
        tpc.setTrainingProgramId(dto.getTrainingProgramId());
        tpc.setCourseId(dto.getCourseId());
        tpc.setCourseCode(dto.getCourseCode());
        tpc.setCourseName(dto.getCourseName());
        tpc.setSemesterId(dto.getSemesterId());
        tpc.setSemesterCode(dto.getSemesterCode());
        tpc.setAcademicYear(dto.getAcademicYear());
        tpc.setIsRequired(dto.getIsRequired());
        tpc.setGroupCode(dto.getGroupCode());
        tpc.setCredits(dto.getCredits());
        tpc.setSortOrder(dto.getSortOrder());
        tpc.setPrerequisiteCourseId(dto.getPrerequisiteCourseId());
        tpc.setNote(dto.getNote());
        tpc.setStatus(dto.getStatus());
        tpc.setUpdatedAt(LocalDateTime.now());
        
        return repository.save(tpc);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        TrainingProgramCourse tpc = getById(id);
        tpc.setIsActive(false);
        // Bỏ deletedAt vì Entity không có
        repository.save(tpc);
    }
}