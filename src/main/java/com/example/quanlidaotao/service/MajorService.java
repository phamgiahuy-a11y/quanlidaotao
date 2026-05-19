package com.example.quanlidaotao.service;

import com.example.quanlidaotao.dto.MajorDTO;
import com.example.quanlidaotao.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface MajorService {
    Page<Major> getAll(Pageable pageable, String keyword);
    List<Major> getAllList();
    Major getById(UUID id);
    
    Major create(MajorDTO dto);
    Major update(UUID id, MajorDTO dto);   // Quan trọng
    void delete(UUID id);
}