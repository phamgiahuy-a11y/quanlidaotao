package com.example.quanlidaotao.service.impl;

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
    public TrainingProgram getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chương trình đào tạo"));
    }

    @Override
    @Transactional
    public TrainingProgram create(TrainingProgram tp) {
        if (repository.findByCode(tp.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã chương trình đã tồn tại!");
        }
        tp.setCreatedAt(LocalDateTime.now());
        tp.setIsActive(true);
        return repository.save(tp);
    }

    @Override
    @Transactional
    public TrainingProgram update(UUID id, TrainingProgram tp) {
        TrainingProgram existing = getById(id);
        tp.setId(id);
        tp.setCreatedAt(existing.getCreatedAt());
        tp.setUpdatedAt(LocalDateTime.now());
        return repository.save(tp);
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