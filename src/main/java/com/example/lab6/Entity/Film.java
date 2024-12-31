package com.example.lab6.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FILMS")
@NamedQuery(name = "Film.findFilmByTitle", query = "SELECT t FROM Film t WHERE t.title = :title")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILMID")
    private int filmId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "RATING")
    private double rating;

    @Column(name = "TICKETPRICE")
    private double ticketPrice;

    @Column(name = "SESSIONTIME")
    private String sessionTime;
}
