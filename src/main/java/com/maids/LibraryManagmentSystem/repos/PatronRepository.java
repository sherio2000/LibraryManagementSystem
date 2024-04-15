package com.maids.LibraryManagmentSystem.repos;
import com.maids.LibraryManagmentSystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long>{
}
