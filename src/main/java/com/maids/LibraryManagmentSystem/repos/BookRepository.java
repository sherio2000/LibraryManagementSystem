package com.maids.LibraryManagmentSystem.repos;

import com.maids.LibraryManagmentSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
