package com.example.quanlidaotao.repository;

import com.example.quanlidaotao.entity.Course;
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
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Page<Course> findByIsActiveTrue(Pageable pageable);
    List<Course> findByIsActiveTrue();
    Optional<Course> findByCode(String code);

    @Query("SELECT c FROM Course c WHERE c.isActive = true AND (LOWER(c.code) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(c.name) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<Course> searchActive(@Param("kw") String keyword, Pageable pageable);
}