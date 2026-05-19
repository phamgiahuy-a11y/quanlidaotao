package com.example.quanlidaotao.service;

import com.example.quanlidaotao.entity.TrainingProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface TrainingProgramService {
    Page<TrainingProgram> getAll(Pageable pageable, String keyword);
    TrainingProgram getById(UUID id);
    TrainingProgram create(TrainingProgram trainingProgram);
    TrainingProgram update(UUID id, TrainingProgram trainingProgram);
    void delete(UUID id);
}