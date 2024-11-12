package com.example.lab2.Repositories;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film {
    private Long id;
    private String title;
    private double rating;
    private double ticketPrice;
    private String sessionTime;
}
