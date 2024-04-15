package com.maids.LibraryManagmentSystem.services.impl;

import com.maids.LibraryManagmentSystem.entities.Patron;
import com.maids.LibraryManagmentSystem.services.IPatronService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.maids.LibraryManagmentSystem.repos.PatronRepository;

@Service
public class PatronService implements IPatronService {


    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron newPatron) {
        Patron existingPatron = getPatronById(id);
        existingPatron.setName(newPatron.getName());
        existingPatron.setPhoneNumber(newPatron.getPhoneNumber());
        existingPatron.setAddressLine1(newPatron.getAddressLine1());
        existingPatron.setAddressLine2(newPatron.getAddressLine2());
        existingPatron.setCountry(newPatron.getCountry());
        return patronRepository.save(existingPatron);
    }

    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patron not found with id: " + id);
        }
        patronRepository.deleteById(id);
    }
}
