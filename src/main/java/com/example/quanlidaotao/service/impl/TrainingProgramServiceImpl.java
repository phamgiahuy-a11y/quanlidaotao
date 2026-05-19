package com.example.quanlidaotao.service.impl;

import com.example.quanlidaotao.dto.TrainingProgramDTO;
import com.example.quanlidaotao.entity.TrainingProgram;
import com.example.quanlidaotao.exception.ResourceNotFoundException;
import com.example.quanlidaotao.repository.TrainingProgramRepository;
import com.example.quanlidaotao.service.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {

    private final TrainingProgramRepository repository;

    @Override
    public Page<TrainingProgram> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            String k = keyword.trim();
            return repository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(k, k, pageable);
        }
        return repository.findAll(pageable);
    }

    @Override
    public List<TrainingProgram> getAllList() {
        return repository.findAll();
    }

    @Override
    public TrainingProgram getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chương trình đào tạo với id: " + id));
    }

    @Override
    @Transactional
    public TrainingProgram create(TrainingProgramDTO dto) {
        if (repository.findByCode(dto.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã chương trình đã tồn tại!");
        }

        TrainingProgram tp = TrainingProgram.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .nameEn(dto.getNameEn())
                .majorId(dto.getMajorId())
                .academicYearId(dto.getAcademicYearId())
                .departmentId(dto.getDepartmentId())
                .degreeLevel(dto.getDegreeLevel())
                .educationType(dto.getEducationType())
                .totalCredits(dto.getTotalCredits())
                .requiredCredits(dto.getRequiredCredits())
                .electiveCredits(dto.getElectiveCredits())
                .admissionYear(dto.getAdmissionYear())
                .durationYears(dto.getDurationYears())
                .status(dto.getStatus())
                .version(dto.getVersion())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();

        return repository.save(tp);
    }

    @Override
    @Transactional
    public TrainingProgram update(UUID id, TrainingProgramDTO dto) {
        TrainingProgram existing = getById(id);

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setNameEn(dto.getNameEn());
        existing.setMajorId(dto.getMajorId());
        existing.setAcademicYearId(dto.getAcademicYearId());
        existing.setDepartmentId(dto.getDepartmentId());
        existing.setDegreeLevel(dto.getDegreeLevel());
        existing.setEducationType(dto.getEducationType());
        existing.setTotalCredits(dto.getTotalCredits());
        existing.setRequiredCredits(dto.getRequiredCredits());
        existing.setElectiveCredits(dto.getElectiveCredits());
        existing.setAdmissionYear(dto.getAdmissionYear());
        existing.setDurationYears(dto.getDurationYears());
        existing.setStatus(dto.getStatus());
        existing.setVersion(dto.getVersion());
        existing.setUpdatedAt(LocalDateTime.now());

        return repository.save(existing);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        TrainingProgram tp = getById(id);
        tp.setDeletedAt(LocalDateTime.now());
        tp.setIsActive(false);
        repository.save(tp);
    }
}