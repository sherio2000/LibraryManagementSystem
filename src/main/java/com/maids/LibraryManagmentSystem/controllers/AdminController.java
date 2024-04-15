package com.maids.LibraryManagmentSystem.controllers;
import com.maids.LibraryManagmentSystem.dto.AdminDTO;
import com.maids.LibraryManagmentSystem.dto.JwtResponseDTO;
import com.maids.LibraryManagmentSystem.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AuthService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtResponseDTO> signup(@RequestBody AdminDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDTO> signin(@RequestBody AdminDTO request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
