package com.example.quanlidaotao.controller;

import com.example.quanlidaotao.dto.ApiResponse;
import com.example.quanlidaotao.dto.CourseDTO;
import com.example.quanlidaotao.entity.Course;
import com.example.quanlidaotao.service.CourseService;
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
@RequestMapping("/api/courses")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Course>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(ApiResponse.success(service.getAll(pageable, keyword)));
    }

    @GetMapping("/list")   // ← Dùng cho dropdown
    public ResponseEntity<ApiResponse<List<Course>>> getList() {
        return ResponseEntity.ok(ApiResponse.success(service.getAllList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@Valid @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo môn học thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable UUID id, @Valid @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật môn học thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa môn học thành công"));
    }
}