package com.example.quanlidaotao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramDTO {

    private UUID id;

    @NotBlank(message = "Mã chương trình đào tạo không được để trống")
    private String code;

    @NotBlank(message = "Tên chương trình đào tạo không được để trống")
    private String name;

    private String nameEn;

    @NotNull(message = "Ngành đào tạo là bắt buộc")
    private UUID majorId;

    private UUID academicYearId;
    private UUID departmentId;

    private String degreeLevel;     // Cử nhân, Thạc sĩ, Tiến sĩ...
    private String educationType;   // Chính quy, Văn bằng 2, Liên thông...

    @Positive(message = "Tổng tín chỉ phải lớn hơn 0")
    private BigDecimal totalCredits;

    private BigDecimal requiredCredits;
    private BigDecimal electiveCredits;

    private LocalDate admissionYear;
    private BigDecimal durationYears;

    private String status;   // DRAFT, ACTIVE, INACTIVE
    private String version;

    private Boolean isActive;
}