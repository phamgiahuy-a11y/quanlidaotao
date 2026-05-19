package com.example.quanlidaotao.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "course_prerequisites")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CoursePrerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID courseId;
    private UUID prerequisiteCourseId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    private Boolean isActive = true;
}