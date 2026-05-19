package com.example.quanlidaotao.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "training_program_courses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainingProgramCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID trainingProgramId;
    private UUID courseId;
    private String courseCode;
    private String courseName;

    private UUID semesterId;
    private String semesterCode;
    private String academicYear;

    private Boolean isRequired = true;
    private String groupCode;
    private BigDecimal credits;
    private Integer sortOrder;

    private UUID prerequisiteCourseId;
    private String note;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    private Boolean isActive = true;
}