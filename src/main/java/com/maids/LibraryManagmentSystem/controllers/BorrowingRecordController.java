package com.maids.LibraryManagmentSystem.controllers;

import com.maids.LibraryManagmentSystem.dto.ErrorResponseDTO;
import com.maids.LibraryManagmentSystem.dto.ResponseDTO;
import com.maids.LibraryManagmentSystem.services.impl.BorrowingRecordService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            borrowingRecordService.borrowBook(bookId, patronId);
            return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "Book borrowed successfully"));
        } catch (ResourceNotFoundException ex) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("/api/borrow/" + bookId + "/patron/" + patronId, ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            borrowingRecordService.returnBook(bookId, patronId);
            return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "Book returned successfully"));
        } catch (ResourceNotFoundException ex) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO("/api/borrow/" + bookId + "/patron/" + patronId, ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }
    }
}
