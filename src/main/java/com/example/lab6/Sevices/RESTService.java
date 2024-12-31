package com.example.lab6.Sevices;

import com.example.lab6.Entity.Film;
import com.example.lab6.Repositories.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RESTService {

    private final FilmRepo filmRepo;

    @Autowired
    public RESTService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    public List<Film> listAllFilms() {
        return filmRepo.findAll();
    }

    public Film getFilmById(int id) {
        try {
            return filmRepo.findById(id).get();
        }
        catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Film with id: " + id + " not found");
        }
    }

    public Film getFilmByTitle(String title) {
        Film film = filmRepo.findFilmByTitle(title);
        if (film == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Film with title: " + title + " not found");
        }
        return film;
    }

    public List<Film> getFilmAboveRating(double rating) {
        return filmRepo.findFilmByRating(rating);
    }

    public List<Film> getFilmByPriceLessThan(double maxPrice) {
        return filmRepo.findByTicketPriceLessThan(maxPrice);
    }

    public void removeFilm(int filmId){
        if (!filmRepo.existsById(filmId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Film with id: " + filmId + " not found"
            );
        }
        filmRepo.deleteById(filmId);
    }

    @Transactional
    public void createFilm(String title, double rating,
                         double ticketPrice, String sessionTime){
        Film newFilm = new Film();
        newFilm.setTitle(title);
        newFilm.setRating(rating);
        newFilm.setTicketPrice(ticketPrice);
        newFilm.setSessionTime(sessionTime);

        filmRepo.save(newFilm);
    }

    @Transactional
    public void updateFilm(int filmId, String title, double rating,
                          double ticketPrice, String sessionTime){
        if (!filmRepo.existsById(filmId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Film with id: " + filmId + " not found"
            );
        }
        filmRepo.save(new Film(filmId, title, rating, ticketPrice, sessionTime));
    }
}
