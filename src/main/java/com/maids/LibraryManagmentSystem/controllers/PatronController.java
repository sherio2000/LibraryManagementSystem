package com.maids.LibraryManagmentSystem.controllers;

import com.maids.LibraryManagmentSystem.dto.PatronDTO;
import com.maids.LibraryManagmentSystem.entities.Patron;
import com.maids.LibraryManagmentSystem.services.impl.PatronService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public ResponseEntity<List<PatronDTO>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        List<PatronDTO> patronDTOs = patrons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(patronDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDTO> getPatronById(@PathVariable Long id) {
        try {
            Patron patron = patronService.getPatronById(id);
            return ResponseEntity.ok(convertToDTO(patron));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PatronDTO> addPatron(@Valid @RequestBody PatronDTO patronDTO) {
        Patron patron = convertToEntity(patronDTO);
        PatronDTO savedPatronDTO = convertToDTO(patronService.addPatron(patron));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatronDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDTO> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDTO patronDTO) {
        Patron patron = convertToEntity(patronDTO);
        PatronDTO updatedPatronDTO = convertToDTO(patronService.updatePatron(id, patron));
        return ResponseEntity.ok(updatedPatronDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private PatronDTO convertToDTO(Patron patron) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(patron, PatronDTO.class);
    }

    private Patron convertToEntity(PatronDTO patronDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(patronDTO, Patron.class);
    }
}
