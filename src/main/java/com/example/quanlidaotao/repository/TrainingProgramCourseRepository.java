package com.example.quanlidaotao.repository;

import com.example.quanlidaotao.entity.TrainingProgramCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrainingProgramCourseRepository extends JpaRepository<TrainingProgramCourse, UUID> {

    List<TrainingProgramCourse> findByTrainingProgramId(UUID trainingProgramId);
    List<TrainingProgramCourse> findByTrainingProgramIdOrderBySortOrderAsc(UUID trainingProgramId);

    Page<TrainingProgramCourse> findAll(Pageable pageable);   // ← Quan trọng
}