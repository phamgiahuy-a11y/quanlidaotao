package com.example.quanlidaotao.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "training_programs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;
    private String name;
    private String nameEn;
    private UUID majorId;
    private UUID academicYearId;
    private UUID departmentId;

    private String degreeLevel;
    private String educationType;

    private BigDecimal totalCredits;
    private BigDecimal requiredCredits;
    private BigDecimal electiveCredits;

    private LocalDate admissionYear;
    private BigDecimal durationYears;

    private String status;
    private String version;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    private Boolean isActive = true;
}