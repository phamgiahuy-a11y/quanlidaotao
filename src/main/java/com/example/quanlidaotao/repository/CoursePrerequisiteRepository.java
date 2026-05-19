package com.example.quanlidaotao.repository;

import com.example.quanlidaotao.entity.CoursePrerequisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CoursePrerequisiteRepository extends JpaRepository<CoursePrerequisite, UUID> {
    
    List<CoursePrerequisite> findByCourseId(UUID courseId);
}