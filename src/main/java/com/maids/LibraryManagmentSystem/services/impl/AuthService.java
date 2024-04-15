package com.maids.LibraryManagmentSystem.services.impl;

import com.maids.LibraryManagmentSystem.dto.AdminDTO;
import com.maids.LibraryManagmentSystem.dto.JwtResponseDTO;
import com.maids.LibraryManagmentSystem.entities.Admin;
import com.maids.LibraryManagmentSystem.repos.AdminRepository;
import com.maids.LibraryManagmentSystem.services.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AdminRepository adminRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDTO signup(AdminDTO request) {
        var user = Admin.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).build();
        adminRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtResponseDTO.builder().token(jwt).build();
    }

    @Override
    public JwtResponseDTO signin(AdminDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtResponseDTO.builder().token(jwt).build();
    }
}
