package com.example.quanlidaotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Trang mặc định khi vào localhost:8080
    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }

    // Trang quản lý chính
    @GetMapping("/quan-ly-chuong-trinh")
    public String quanLy() {
        return "forward:/index.html";
    }

    // Test
    @GetMapping("/test")
    public String test() {
        return "forward:/index.html";
    }
}