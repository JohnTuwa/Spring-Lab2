package com.example.lab2.Repositories;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class FilmRepository{

    private List<Film> films = new ArrayList<>();

    public void addFilm(long id,String title,double rating,double ticketPrice,String sessionTime) {
        Film film = new Film(id,title,rating,ticketPrice,sessionTime);
        films.add(film);
    }

    public String getById(long id){
        for (Film film : films) {
            if (film.getId() == id){
                return film.getTitle();
            }
        }
        return null;
    }

    public List<Film> findAll() {
        return films;
    }
}