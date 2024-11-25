package com.example.lab2.Repositories;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepository{

    private List<Film> films;

    public FilmRepository() {
        int rows = 8;
        int seats = 16;
        films = new ArrayList<>();
        films.add(new Film(1, "Inception", 8.8, 12.5, "18:00", new boolean[rows][seats]));
        films.add(new Film(2, "The Matrix", 8.7, 10.0, "20:00", new boolean[rows][seats]));
        films.add(new Film(3, "Interstellar", 8.6, 15.0, "21:00", new boolean[rows][seats]));
    }

    public void addFilm(long id,String title,double rating,double ticketPrice,String sessionTime,boolean[][] seats) {
        Film film = new Film(id,title,rating,ticketPrice,sessionTime,seats);
        films.add(film);
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