package com.example.lab5.Sevices;

import com.example.lab5.Repositories.Film;
import com.example.lab5.Repositories.OracleFilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RESTService {
    private final OracleFilmRepo oracleFilmRepo;

    @Autowired
    public RESTService(OracleFilmRepo oracleFilmRepo) {
        this.oracleFilmRepo = oracleFilmRepo;
    }

    public void removeFilm(long filmId){
        oracleFilmRepo.removeFilm(filmId);
    }

    public void createFilm(long filmId, String title, double rating,
                         double ticketPrice, String sessionTime){
        oracleFilmRepo.create(new Film(filmId, title, rating, ticketPrice, sessionTime));
    }
    public void updateFilm(long filmId, String title, double rating,
                         double ticketPrice, String sessionTime){
        oracleFilmRepo.update(new Film(filmId, title, rating, ticketPrice, sessionTime));
    }
}
