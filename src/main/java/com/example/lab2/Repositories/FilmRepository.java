package com.example.lab2.Repositories;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class FilmRepository{

    private List<Film> films = new ArrayList<>();

    public void addFilm(Long id,String title,double rating,double ticketPrice,String sessionTime) {
        Film film = new Film(id,title,rating,ticketPrice,sessionTime);
        films.add(film);
    }

    public List<Film> findAll() {
        return films;
    }


}