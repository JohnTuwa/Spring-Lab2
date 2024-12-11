package com.example.lab2.Controllers;

import com.example.lab2.Repositories.Film;
import com.example.lab2.Repositories.FilmRepository;
import com.example.lab2.Sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final FilmRepository filmRepository;
    private final UserService userService;

    @Autowired
    public UserController(FilmRepository filmRepository, UserService userService) {
        this.filmRepository = filmRepository;
        this.userService = userService;
    }

    @PostMapping("/user")
    private String loginUser(Model model) {
        List<Film> allFilms = filmRepository.findAll();
        model.addAttribute("allFilms", allFilms);
        return "userPage";
    }

    @PostMapping("/user/buyTicket")
    private String buyTicket(long filmId, Model model) {
        boolean[][] seats = filmRepository.getById(filmId).getSeats();
        model.addAttribute("seats", seats);
        model.addAttribute("filmId", filmId);
        return "ticket";
    }
    @PostMapping("/user/buyTicket/status")
    private String buyTicket(int row, int seat, long filmId, Model model) {
        boolean status = userService.checkSeat(row, seat, filmId);
        model.addAttribute("status", status);
        return "buyStatus";
    }
}
