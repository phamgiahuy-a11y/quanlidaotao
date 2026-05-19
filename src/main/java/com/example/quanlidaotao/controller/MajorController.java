package com.example.quanlidaotao.controller;

import com.example.quanlidaotao.dto.ApiResponse;
import com.example.quanlidaotao.entity.Major;
import com.example.quanlidaotao.service.MajorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/majors")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MajorController {

    private final MajorService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Major>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String keyword) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(ApiResponse.success(service.getAll(pageable, keyword)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Major>> create(@Valid @RequestBody Major major) {
        return ResponseEntity.ok(ApiResponse.success(service.create(major), "Tạo ngành thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> update(@PathVariable UUID id, @Valid @RequestBody Major major) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, major), "Cập nhật thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}