package com.example.lab6.Repositories;

import com.example.lab6.Entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, Integer> {

    List<Film> findAll();

    @Query(name = "Film.findFilmByTitle")
    Film findFilmByTitle(String title);

    @Query("SELECT f FROM Film f WHERE f.rating > :rating")
    List<Film> findFilmByRating(double rating);

    List<Film> findByTicketPriceLessThan(double maxPrice);
}
