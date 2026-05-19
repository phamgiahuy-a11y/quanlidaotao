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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {

    private final TrainingProgramRepository repository;

    @Override
    public Page<TrainingProgram> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return repository.searchActive(keyword.trim(), pageable);
        }
        return repository.findByIsActiveTrue(pageable);
    }

    @Override
    public List<TrainingProgram> getAllList() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public TrainingProgram getById(UUID id) {
        return repository.findById(id)
                .filter(TrainingProgram::getIsActive)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy CTĐT"));
    }

    @Override
    @Transactional
    public TrainingProgram create(TrainingProgramDTO dto) {
        Optional<TrainingProgram> existing = repository.findByCode(dto.getCode());
        TrainingProgram tp;

        if (existing.isPresent()) {
            tp = existing.get();
            if (tp.getIsActive()) {
                throw new RuntimeException("Mã Chương trình đào tạo đã tồn tại!");
            }
            tp.setIsActive(true);
        } else {
            tp = new TrainingProgram();
            tp.setCreatedAt(LocalDateTime.now());
        }

        tp.setCode(dto.getCode());
        tp.setName(dto.getName());
        tp.setNameEn(dto.getNameEn());
        tp.setMajorId(dto.getMajorId());
        tp.setAcademicYearId(dto.getAcademicYearId());
        tp.setDepartmentId(dto.getDepartmentId());
        tp.setDegreeLevel(dto.getDegreeLevel());
        tp.setEducationType(dto.getEducationType());
        tp.setTotalCredits(dto.getTotalCredits());
        tp.setRequiredCredits(dto.getRequiredCredits());
        tp.setElectiveCredits(dto.getElectiveCredits());
        tp.setAdmissionYear(dto.getAdmissionYear());
        tp.setDurationYears(dto.getDurationYears());
        tp.setStatus(dto.getStatus());
        tp.setVersion(dto.getVersion());
        tp.setIsActive(true);

        return repository.save(tp);
    }

    @Override
    @Transactional
    public TrainingProgram update(UUID id, TrainingProgramDTO dto) {
        TrainingProgram tp = getById(id);
        tp.setCode(dto.getCode());
        tp.setName(dto.getName());
        tp.setNameEn(dto.getNameEn());
        tp.setMajorId(dto.getMajorId());
        tp.setAcademicYearId(dto.getAcademicYearId());
        tp.setDepartmentId(dto.getDepartmentId());
        tp.setDegreeLevel(dto.getDegreeLevel());
        tp.setEducationType(dto.getEducationType());
        tp.setTotalCredits(dto.getTotalCredits());
        tp.setRequiredCredits(dto.getRequiredCredits());
        tp.setElectiveCredits(dto.getElectiveCredits());
        tp.setAdmissionYear(dto.getAdmissionYear());
        tp.setDurationYears(dto.getDurationYears());
        tp.setStatus(dto.getStatus());
        tp.setVersion(dto.getVersion());
        tp.setUpdatedAt(LocalDateTime.now());
        return repository.save(tp);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        TrainingProgram tp = getById(id);
        tp.setIsActive(false);
        // Bỏ deletedAt vì Entity không có
        repository.save(tp);
    }
}