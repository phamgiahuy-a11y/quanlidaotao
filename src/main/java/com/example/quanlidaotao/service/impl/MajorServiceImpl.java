package com.example.quanlidaotao.service.impl;

import com.example.quanlidaotao.dto.MajorDTO;
import com.example.quanlidaotao.entity.Major;
import com.example.quanlidaotao.exception.ResourceNotFoundException;
import com.example.quanlidaotao.repository.MajorRepository;
import com.example.quanlidaotao.service.MajorService;
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
public class MajorServiceImpl implements MajorService {

    private final MajorRepository repository;

    @Override
    public Page<Major> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            String k = keyword.trim();
            return repository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(k, k, pageable);
        }
        return repository.findAll(pageable);
    }

    @Override
    public List<Major> getAllList() {
        return repository.findAll();
    }

    @Override
    public Major getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ngành với id: " + id));
    }

    @Override
    @Transactional
    public Major create(MajorDTO dto) {
        if (repository.findByCode(dto.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã ngành đã tồn tại!");
        }

        Major major = Major.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .departmentId(dto.getDepartmentId())
                .effectiveDate(dto.getEffectiveDate())
                .expiryDate(dto.getExpiryDate())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();

        return repository.save(major);
    }

    @Override
    @Transactional
    public Major update(UUID id, MajorDTO dto) {
        Major existing = getById(id);

        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setDepartmentId(dto.getDepartmentId());
        existing.setEffectiveDate(dto.getEffectiveDate());
        existing.setExpiryDate(dto.getExpiryDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return repository.save(existing);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Major major = getById(id);
        major.setDeletedAt(LocalDateTime.now());
        major.setIsActive(false);
        repository.save(major);
    }
}