package com.example.lab2.Repositories;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Film {
    private long id;
    private String title;
    private double rating;
    private double ticketPrice;
    private String sessionTime;
}
