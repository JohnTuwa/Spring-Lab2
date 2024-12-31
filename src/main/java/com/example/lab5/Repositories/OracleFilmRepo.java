package com.example.lab5.Repositories;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Types;
import java.util.List;
import java.util.Objects;
@Repository
@Primary
@AllArgsConstructor

public class OracleFilmRepo implements Films {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_SQL = "SELECT * FROM FILMS";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM FILMS WHERE filmId=?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM FILMS WHERE filmId=?";
    private static final String UPDATE_SQL = "UPDATE FILMS SET title=?, rating=?," +
                                             "ticketPrice=?, sessionTime=? WHERE filmId=?";
    private static final String INSERT_SQL = "INSERT INTO FILMS (filmId, title, rating, ticketPrice, sessionTime)" +
                                             "VALUES (?, ?, ?, ?, ?)";
    private static final RowMapper<Film> ROW_MAPPER = (rs, rowNum) -> new Film(
            rs.getLong(1),
            rs.getString(2),
            rs.getDouble(3),
            rs.getDouble(4),
            rs.getString(5)
    );


    @Override
    public Film getById(long filmId) {
        try{
            return jdbcTemplate.queryForObject(FIND_BY_ID_SQL, ROW_MAPPER, filmId);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "Film with id: " + filmId + " not found");
        }
    }

    @Override
    public List<Film> findAll() {
        System.out.println(jdbcTemplate.query(FIND_ALL_SQL, ROW_MAPPER));
        return jdbcTemplate.query(FIND_ALL_SQL, ROW_MAPPER);
    }

    @Override
    public void removeFilm(long filmId) {
        jdbcTemplate.update(DELETE_BY_ID_SQL, filmId);

    }


    public Film update(Film film) {
        jdbcTemplate.update(UPDATE_SQL, film.getTitle(), film.getRating(),
                film.getTicketPrice(), film.getSessionTime(), film.getFilmId());
        return film;
    }

    public Film create(Film film) {
        long filmId = film.getFilmId();
        try {
            jdbcTemplate.update(INSERT_SQL,
                    filmId,
                    film.getTitle(),
                    film.getRating(),
                    film.getTicketPrice(),
                    film.getSessionTime()
            );
            return film;

        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409),
                    "Film with id: " + filmId +" already exists");
        }
    }
}
