package com.maids.LibraryManagmentSystem.services.impl;

import com.maids.LibraryManagmentSystem.entities.Admin;
import com.maids.LibraryManagmentSystem.repos.AdminRepository;
import com.maids.LibraryManagmentSystem.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return adminRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

}
