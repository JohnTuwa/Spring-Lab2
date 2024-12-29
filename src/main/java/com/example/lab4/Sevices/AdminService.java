package com.example.lab4.Sevices;

import com.example.lab4.Repositories.Film;
import com.example.lab4.Repositories.FilmRepository;
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

    public void removeFilm(long filmId){
        filmRepository.removeFilm(filmId);
    }

    public void newFilm(String title, double rating,
                           double ticketPrice, String sessionTime){
        filmRepository.addFilm(title, rating, ticketPrice, sessionTime);
    }

    public int checkStatus(String title, double rating, double ticketPrice, String sessionTime){
        return switch (title.isEmpty() ? 1
                : rating < 0 ? 2
                : ticketPrice < 0 ? 3
                : sessionTime.isEmpty() ? 4
                : 5) {
            case 1 -> 1; // Empty name
            case 2 -> 2; // Negative rating
            case 3 -> 3; // Negative ticket price
            case 4 -> 4; // Empty session time
            default -> 5; // All inputs are valid
        };
    }
}
