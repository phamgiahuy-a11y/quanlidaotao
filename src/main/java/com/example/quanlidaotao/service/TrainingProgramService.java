package com.example.quanlidaotao.service;

import com.example.quanlidaotao.dto.TrainingProgramDTO;
import com.example.quanlidaotao.entity.TrainingProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface TrainingProgramService {
    Page<TrainingProgram> getAll(Pageable pageable, String keyword);
    List<TrainingProgram> getAllList();
    TrainingProgram getById(UUID id);
    TrainingProgram create(TrainingProgramDTO dto);
    TrainingProgram update(UUID id, TrainingProgramDTO dto);
    void delete(UUID id);
}