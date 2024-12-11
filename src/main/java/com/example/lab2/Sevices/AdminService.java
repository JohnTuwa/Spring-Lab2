package com.example.lab2.Sevices;

import com.example.lab2.Repositories.Film;
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

    public void editFilm(long filmId, String title, double rating,
                         double ticketPrice, String sessionTime){
        Film film = filmRepository.getById(filmId);

        film.setTitle(title);
        film.setRating(rating);
        film.setTicketPrice(ticketPrice);
        film.setSessionTime(sessionTime);
    }

    public boolean removeFilm(long filmId){
        filmRepository.removeFilm(filmId);
        return this.checkIfExists(filmId);
    }

    public void newFilm(String title, double rating,
                           double ticketPrice, String sessionTime){
        filmRepository.addFilm(title, rating, ticketPrice, sessionTime);
    }
}
