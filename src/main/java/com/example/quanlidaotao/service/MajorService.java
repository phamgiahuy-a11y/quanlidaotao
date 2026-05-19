package com.example.quanlidaotao.service;

import com.example.quanlidaotao.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface MajorService {
    Page<Major> getAll(Pageable pageable, String keyword);
    Major getById(UUID id);
    Major create(Major major);
    Major update(UUID id, Major major);
    void delete(UUID id);
}