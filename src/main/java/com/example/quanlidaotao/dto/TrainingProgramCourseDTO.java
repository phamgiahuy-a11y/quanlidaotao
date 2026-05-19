package com.example.quanlidaotao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingProgramCourseDTO {

    private UUID id;

    @NotNull(message = "Chương trình đào tạo là bắt buộc")
    private UUID trainingProgramId;

    @NotNull(message = "Môn học là bắt buộc")
    private UUID courseId;

    private String courseCode;   // Để hiển thị
    private String courseName;   // Để hiển thị

    private UUID semesterId;
    private String semesterCode;
    private String academicYear;

    private Boolean isRequired = true;

    private String groupCode;     // Nhóm tự chọn (ví dụ: CS1, CS2)
    private Integer sortOrder;

    @Positive
    private BigDecimal credits;

    private UUID prerequisiteCourseId;   // Học phần tiên quyết
    private String note;
    private String status;

    private Boolean isActive;
}