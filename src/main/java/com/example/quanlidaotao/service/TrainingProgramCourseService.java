package com.example.quanlidaotao.service;

import com.example.quanlidaotao.dto.TrainingProgramCourseDTO;
import com.example.quanlidaotao.entity.TrainingProgramCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface TrainingProgramCourseService {
    Page<TrainingProgramCourse> getAll(Pageable pageable);
    List<TrainingProgramCourse> getAllList();
    List<TrainingProgramCourse> getByTrainingProgramId(UUID trainingProgramId);
    TrainingProgramCourse getById(UUID id);
    TrainingProgramCourse create(TrainingProgramCourseDTO dto);
    TrainingProgramCourse update(UUID id, TrainingProgramCourseDTO dto);
    void delete(UUID id);
}