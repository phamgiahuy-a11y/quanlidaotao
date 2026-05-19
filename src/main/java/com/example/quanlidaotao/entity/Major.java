package com.example.quanlidaotao.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "majors")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID departmentId;
    private String code;
    private String name;
    private String description;

    private LocalDateTime effectiveDate;
    private LocalDateTime expiryDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    private LocalDateTime deletedAt;
    private UUID deletedBy;
    private Boolean isActive = true;
}