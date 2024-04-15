package com.maids.LibraryManagmentSystem.services;

public interface IBorrowingRecordService {
    public void borrowBook(Long bookId, Long patronId);
    public void returnBook(Long bookId, Long patronId);
}
