package com.example.lab4.Sevices;

import com.example.lab4.Repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final FilmRepository filmRepository;

    @Autowired
    public UserService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public boolean isSeatTaken(int row, int col, long filmId) {
        boolean[][] seats = filmRepository.getById(filmId).getSeats();
        if (!seats[row][col]) {
            seats[row][col] = true;
            return false;
        }
        else return true;
    }
}