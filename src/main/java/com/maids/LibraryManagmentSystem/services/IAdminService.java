package com.maids.LibraryManagmentSystem.services;

import com.maids.LibraryManagmentSystem.entities.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IAdminService {
    UserDetailsService userDetailsService();
}
