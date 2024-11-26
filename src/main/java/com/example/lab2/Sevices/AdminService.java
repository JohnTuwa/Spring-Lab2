package com.example.lab2.Sevices;

import com.example.lab2.Repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final FilmRepository filmRepository;

    @Autowired
    public AdminService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public boolean checkIfExists(long id) {
        return filmRepository.filmExists(id);
    }
}
