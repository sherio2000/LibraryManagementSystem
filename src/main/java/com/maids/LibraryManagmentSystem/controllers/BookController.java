package com.maids.LibraryManagmentSystem.controllers;

import com.maids.LibraryManagmentSystem.dto.BookDTO;
import com.maids.LibraryManagmentSystem.entities.Book;
import com.maids.LibraryManagmentSystem.services.impl.BookService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookDTO> bookDTOs = books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(convertToDTO(book));
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        BookDTO savedBookDTO = convertToDTO(bookService.addBook(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        BookDTO updatedBookDTO = convertToDTO(bookService.updateBook(id, book));
        return ResponseEntity.ok(updatedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private BookDTO convertToDTO(Book book) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(book, BookDTO.class);
    }

    private Book convertToEntity(BookDTO bookDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDTO, Book.class);
    }
}
