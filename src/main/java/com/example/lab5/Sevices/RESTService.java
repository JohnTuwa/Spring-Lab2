package com.example.lab5.Sevices;

import com.example.lab5.Repositories.Film;
import com.example.lab5.Repositories.OracleFilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RESTService {
    private final OracleFilmRepo oracleFilmRepo;

    @Autowired
    public RESTService(OracleFilmRepo oracleFilmRepo) {
        this.oracleFilmRepo = oracleFilmRepo;
    }

    public void removeFilm(long filmId){
        int rowsAffected = oracleFilmRepo.removeFilm(filmId);
        if (rowsAffected == 0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "Film with id: " + filmId + " not found");
        }
    }

    public void createFilm(long filmId, String title, double rating,
                         double ticketPrice, String sessionTime){
        oracleFilmRepo.create(new Film(filmId, title, rating, ticketPrice, sessionTime));
    }
    public void updateFilm(long filmId, String title, double rating,
                         double ticketPrice, String sessionTime){
        int rowsAffected = oracleFilmRepo.update(new Film(filmId, title, rating, ticketPrice, sessionTime));
        if (rowsAffected == 0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "Film with id: " + filmId + " not found");
        }
    }
}
