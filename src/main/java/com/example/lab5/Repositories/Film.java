package com.example.lab5.Repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Film {
    private long filmId;
    private String title;
    private double rating;
    private double ticketPrice;
    private String sessionTime;
}
