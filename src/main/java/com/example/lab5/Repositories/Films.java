package com.example.lab5.Repositories;

import java.util.List;

public interface Films {
    Film getById(long id);
    List<Film> findAll();
    int removeFilm(long id);
    int update(Film film);
    Film create(Film film);
}
