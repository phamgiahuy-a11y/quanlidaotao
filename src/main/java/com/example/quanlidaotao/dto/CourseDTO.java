package com.example.quanlidaotao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CourseDTO {
    private UUID id;
    private UUID departmentId;

    @NotBlank(message = "Mã môn học không được để trống")
    private String code;

    @NotBlank(message = "Tên môn học không được để trống")
    private String name;

    private String nameEn;
    @Positive(message = "Số tín chỉ phải lớn hơn 0")
    private BigDecimal credits;

    private String courseType;
    private BigDecimal theoryHours;
    private BigDecimal practiceHours;
    private BigDecimal selfStudyHours;
    private String description;
    private Boolean isActive;
}