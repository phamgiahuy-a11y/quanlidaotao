package com.example.quanlidaotao.controller;

import com.example.quanlidaotao.dto.ApiResponse;
import com.example.quanlidaotao.entity.TrainingProgram;
import com.example.quanlidaotao.service.TrainingProgramService;
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
@RequestMapping("/api/training-programs")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TrainingProgramController {

    private final TrainingProgramService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TrainingProgram>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String keyword) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.ok(ApiResponse.success(service.getAll(pageable, keyword)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingProgram>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingProgram>> create(@Valid @RequestBody TrainingProgram program) {
        return ResponseEntity.ok(ApiResponse.success(service.create(program), "Tạo chương trình thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingProgram>> update(@PathVariable UUID id, @Valid @RequestBody TrainingProgram program) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, program), "Cập nhật thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}