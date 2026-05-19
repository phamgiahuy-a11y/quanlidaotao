package com.example.quanlidaotao.service.impl;

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
    public Major getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ngành đào tạo"));
    }

    @Override
    @Transactional
    public Major create(Major major) {
        if (repository.findByCode(major.getCode()).isPresent()) {
            throw new IllegalArgumentException("Mã ngành đã tồn tại!");
        }
        major.setCreatedAt(LocalDateTime.now());
        major.setIsActive(true);
        return repository.save(major);
    }

    @Override
    @Transactional
    public Major update(UUID id, Major major) {
        Major existing = getById(id);
        major.setId(id);
        major.setCreatedAt(existing.getCreatedAt());
        major.setUpdatedAt(LocalDateTime.now());
        return repository.save(major);
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