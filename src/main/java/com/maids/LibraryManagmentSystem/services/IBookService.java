package com.maids.LibraryManagmentSystem.services;

import com.maids.LibraryManagmentSystem.entities.Book;

import java.util.List;

public interface IBookService {

    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public Book addBook(Book book);
    public Book updateBook(Long id, Book newBook);
    public void deleteBook(Long id);
}
