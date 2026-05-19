package com.example.quanlidaotao.repository;

import com.example.quanlidaotao.entity.TrainingProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, UUID> {
    Page<TrainingProgram> findByIsActiveTrue(Pageable pageable);
    List<TrainingProgram> findByIsActiveTrue();
    Optional<TrainingProgram> findByCode(String code);

    @Query("SELECT t FROM TrainingProgram t WHERE t.isActive = true AND (LOWER(t.code) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(t.name) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<TrainingProgram> searchActive(@Param("kw") String keyword, Pageable pageable);
}