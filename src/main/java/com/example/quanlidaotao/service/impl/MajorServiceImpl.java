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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService {

    private final MajorRepository repository;

    @Override
    public Page<Major> getAll(Pageable pageable, String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return repository.searchActive(keyword.trim(), pageable);
        }
        return repository.findByIsActiveTrue(pageable);
    }

    @Override
    public List<Major> getAllList() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Major getById(UUID id) {
        return repository.findById(id)
                .filter(Major::getIsActive)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy ngành"));
    }

    @Override
    @Transactional
    public Major create(MajorDTO dto) {
        Optional<Major> existing = repository.findByCode(dto.getCode());
        Major major;

        if (existing.isPresent()) {
            major = existing.get();
            if (major.getIsActive()) {
                throw new RuntimeException("Mã ngành đã tồn tại!");
            }
            major.setIsActive(true);
            major.setDeletedAt(null); // Xóa ngày xóa khi khôi phục
        } else {
            major = new Major();
            major.setCreatedAt(LocalDateTime.now());
        }

        major.setCode(dto.getCode());
        major.setName(dto.getName());
        major.setDescription(dto.getDescription());
        major.setDepartmentId(dto.getDepartmentId());
        major.setEffectiveDate(dto.getEffectiveDate());
        major.setExpiryDate(dto.getExpiryDate());
        major.setIsActive(true);

        return repository.save(major);
    }

    @Override
    @Transactional
    public Major update(UUID id, MajorDTO dto) {
        Major major = getById(id);
        major.setCode(dto.getCode());
        major.setName(dto.getName());
        major.setDescription(dto.getDescription());
        major.setDepartmentId(dto.getDepartmentId());
        major.setEffectiveDate(dto.getEffectiveDate());
        major.setExpiryDate(dto.getExpiryDate());
        major.setUpdatedAt(LocalDateTime.now());
        return repository.save(major);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Major major = getById(id);
        major.setIsActive(false);
        major.setDeletedAt(LocalDateTime.now()); // Entity Major có deletedAt
        repository.save(major);
    }
}