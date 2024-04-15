package com.maids.LibraryManagmentSystem.services;

import com.maids.LibraryManagmentSystem.dto.AdminDTO;
import com.maids.LibraryManagmentSystem.dto.JwtResponseDTO;

public interface IAuthService {
    JwtResponseDTO signup(AdminDTO request);
    JwtResponseDTO signin(AdminDTO request);
}
