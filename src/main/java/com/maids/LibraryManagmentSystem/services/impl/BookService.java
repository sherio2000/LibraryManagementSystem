package com.maids.LibraryManagmentSystem.services.impl;
import com.maids.LibraryManagmentSystem.entities.Book;
import com.maids.LibraryManagmentSystem.repos.BookRepository;
import com.maids.LibraryManagmentSystem.services.IBookService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(newBook.getTitle());
        existingBook.setAuthor(newBook.getAuthor());
        existingBook.setPublicationYear(newBook.getPublicationYear());
        existingBook.setIsbn(newBook.getIsbn());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
