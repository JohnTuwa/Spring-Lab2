package com.example.lab4.Repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepository{

    private List<Film> films;
    public long nextId = 1;
    int rows = 2;
    int seats = 4;

    public FilmRepository() {

        films = new ArrayList<>();
        films.add(new Film(nextId++, "Inception", 8.8, 12.5, "18:00", new boolean[rows][seats]));
        films.add(new Film(nextId++, "The Matrix", 8.7, 10.0, "20:00", new boolean[rows][seats]));
        films.add(new Film(nextId++, "Interstellar", 8.6, 15.0, "21:00", new boolean[rows][seats]));
    }

    public void addFilm(String title, double rating, double ticketPrice, String sessionTime) {
        Film film = new Film(nextId++, title, rating, ticketPrice, sessionTime, new boolean[rows][seats]);
        films.add(film);
    }

    public void removeFilm(long id) {
        films.removeIf(film -> film.getId() == id);
    }

    public boolean filmExists(long id) {
        return films.stream().anyMatch(film -> film.getId() == id);
    }

    public Film getById(long id){
        for (Film film : films) {
            if (film.getId() == id){
                return film;
            }
        }
        return null;
    }

    public List<Film> findAll() {
        return films;
    }
}