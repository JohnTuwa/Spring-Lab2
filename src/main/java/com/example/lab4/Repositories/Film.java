package com.example.lab4.Repositories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Film {
    private long id;
    private String title;
    private double rating;
    private double ticketPrice;
    private String sessionTime;
    private boolean[][] seats;
}
