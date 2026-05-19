package com.example.quanlidaotao.controller;

import com.example.quanlidaotao.dto.ApiResponse;
import com.example.quanlidaotao.entity.TrainingProgramCourse;
import com.example.quanlidaotao.service.TrainingProgramCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/training-program-courses")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TrainingProgramCourseController {

    private final TrainingProgramCourseService service;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TrainingProgramCourse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingProgramCourse> data = service.getAll(pageable);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<ApiResponse<List<TrainingProgramCourse>>> getByProgram(@PathVariable UUID programId) {
        List<TrainingProgramCourse> list = service.getByTrainingProgramId(programId);
        return ResponseEntity.ok(ApiResponse.success(list, "Lấy danh sách học phần thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingProgramCourse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TrainingProgramCourse>> create(@Valid @RequestBody TrainingProgramCourse tpc) {
        TrainingProgramCourse saved = service.create(tpc);
        return ResponseEntity.ok(ApiResponse.success(saved, "Thêm học phần thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainingProgramCourse>> update(@PathVariable UUID id, @Valid @RequestBody TrainingProgramCourse tpc) {
        TrainingProgramCourse updated = service.update(id, tpc);
        return ResponseEntity.ok(ApiResponse.success(updated, "Cập nhật thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa thành công"));
    }
}