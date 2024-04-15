package com.maids.LibraryManagmentSystem.services;

import com.maids.LibraryManagmentSystem.entities.Patron;

import java.util.List;

public interface IPatronService {
    public List<Patron> getAllPatrons();
    public Patron getPatronById(Long id);
    public Patron addPatron(Patron patron);
    public Patron updatePatron(Long id, Patron newPatron);
    public void deletePatron(Long id);
}
