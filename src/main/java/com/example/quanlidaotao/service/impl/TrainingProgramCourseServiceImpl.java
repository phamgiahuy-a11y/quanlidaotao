package com.example.quanlidaotao.service.impl;

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
        return repository.findAll(pageable);
    }

    @Override
    public List<TrainingProgramCourse> getByTrainingProgramId(UUID trainingProgramId) {
        return repository.findByTrainingProgramIdOrderBySortOrderAsc(trainingProgramId);
    }

    @Override
    public TrainingProgramCourse getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy học phần: " + id));
    }

    @Override
    @Transactional
    public TrainingProgramCourse create(TrainingProgramCourse tpc) {
        tpc.setCreatedAt(LocalDateTime.now());
        tpc.setIsActive(true);
        return repository.save(tpc);
    }

    @Override
    @Transactional
    public TrainingProgramCourse update(UUID id, TrainingProgramCourse tpc) {
        TrainingProgramCourse existing = getById(id);
        tpc.setId(id);
        tpc.setCreatedAt(existing.getCreatedAt());
        tpc.setUpdatedAt(LocalDateTime.now());
        return repository.save(tpc);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        TrainingProgramCourse tpc = getById(id);
        tpc.setDeletedAt(LocalDateTime.now());
        tpc.setIsActive(false);
        repository.save(tpc);
    }
}