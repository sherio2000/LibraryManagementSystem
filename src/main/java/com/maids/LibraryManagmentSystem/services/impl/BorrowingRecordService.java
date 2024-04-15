package com.maids.LibraryManagmentSystem.services.impl;

import com.maids.LibraryManagmentSystem.entities.Book;
import com.maids.LibraryManagmentSystem.entities.BorrowingRecord;
import com.maids.LibraryManagmentSystem.entities.Patron;
import com.maids.LibraryManagmentSystem.repos.BookRepository;
import com.maids.LibraryManagmentSystem.repos.BorrowingRecordRepository;
import com.maids.LibraryManagmentSystem.repos.PatronRepository;
import com.maids.LibraryManagmentSystem.services.IBorrowingRecordService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class BorrowingRecordService implements IBorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public void borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + patronId));

        // Create a new BorrowingRecord
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());

        // Save the BorrowingRecord
        borrowingRecordRepository.save(borrowingRecord);
    }

    public void returnBook(Long bookId, Long patronId) {
        // Find the BorrowingRecord by bookId and patronId
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found for bookId: " + bookId + " and patronId: " + patronId));

        // Update the return date
        borrowingRecord.setReturnDate(LocalDate.now());

        // Save the updated BorrowingRecord
        borrowingRecordRepository.save(borrowingRecord);
    }
}

