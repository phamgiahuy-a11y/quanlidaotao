package com.example.quanlidaotao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MajorDTO {
    private UUID id;
    private UUID departmentId;

    @NotBlank(message = "Mã ngành không được để trống")
    @Size(max = 20)
    private String code;

    @NotBlank(message = "Tên ngành không được để trống")
    private String name;

    private String description;
    private LocalDateTime effectiveDate;
    private LocalDateTime expiryDate;
    private Boolean isActive;
}