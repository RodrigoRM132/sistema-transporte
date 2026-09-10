package com.sistema.rafael.admin;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "Bienvenido ADMIN";
    }
}