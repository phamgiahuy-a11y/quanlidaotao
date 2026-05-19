package com.example.quanlidaotao.controller;

import com.example.quanlidaotao.dto.ApiResponse;
import com.example.quanlidaotao.dto.MajorDTO;
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

import java.util.List;
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
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(ApiResponse.success(service.getAll(pageable, keyword)));
    }

    @GetMapping("/list")   // ← Dùng cho dropdown
    public ResponseEntity<ApiResponse<List<Major>>> getList() {
        return ResponseEntity.ok(ApiResponse.success(service.getAllList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Major>> create(@Valid @RequestBody MajorDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo ngành thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Major>> update(@PathVariable UUID id, @Valid @RequestBody MajorDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật ngành thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa ngành thành công"));
    }
}