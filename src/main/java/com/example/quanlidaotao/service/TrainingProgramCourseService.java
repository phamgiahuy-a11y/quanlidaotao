package com.example.quanlidaotao.service;

import com.example.quanlidaotao.entity.TrainingProgramCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface TrainingProgramCourseService {
    Page<TrainingProgramCourse> getAll(Pageable pageable);
    List<TrainingProgramCourse> getByTrainingProgramId(UUID trainingProgramId);
    TrainingProgramCourse getById(UUID id);
    TrainingProgramCourse create(TrainingProgramCourse tpc);
    TrainingProgramCourse update(UUID id, TrainingProgramCourse tpc);
    void delete(UUID id);
}