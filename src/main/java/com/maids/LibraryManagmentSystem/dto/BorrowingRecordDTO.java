package com.maids.LibraryManagmentSystem.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingRecordDTO {
    private Long id;
    private BookDTO book;
    private PatronDTO patron;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
