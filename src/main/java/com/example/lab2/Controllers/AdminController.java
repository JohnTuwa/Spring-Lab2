package com.example.lab2.Controllers;

import com.example.lab2.Repositories.Film;
import com.example.lab2.Repositories.FilmRepository;
import com.example.lab2.Sevices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final FilmRepository filmRepository;
    private final AdminService adminService;

    @Autowired
    public AdminController(FilmRepository filmRepository, AdminService adminService) {
        this.filmRepository = filmRepository;
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    private String loginAdmin(Model model) {
        List<Film> allFilms = filmRepository.findAll();
        model.addAttribute("allFilms", allFilms);
        return "adminPage";
    }

    @PostMapping("/admin/edit")
    private String editFilm(long filmId, Model model){
        Film film = filmRepository.getById(filmId);

        String title = film.getTitle();
        double rating = film.getRating();
        double ticketPrice = film.getTicketPrice();
        String sessionTime = film.getSessionTime();

        model.addAttribute("filmId", filmId);
        model.addAttribute("title", title);
        model.addAttribute("rating", rating);
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("sessionTime", sessionTime);
        return "editFilm";
    }

    @PostMapping("/admin/edit/success")
    private String editSuccess(long filmId, String title, double rating,
                               double ticketPrice, String sessionTime){
        adminService.editFilm(filmId, title, rating, ticketPrice, sessionTime);

        return "editSuccess";
    }

    @PostMapping("/admin/remove")
    private String removeFilm(long filmId, Model model){
        String movieName = filmRepository.getById(filmId).getTitle();
        model.addAttribute("movieName", movieName);
        model.addAttribute("filmId", filmId);
        return "removeFilm";
    }

    @PostMapping("/admin/remove/success")
    private String removeSuccess(long filmId, Model model) {
        boolean success = adminService.removeFilm(filmId);
        model.addAttribute("success", success);
        return "removeConfirmation";
    }

    @PostMapping("/admin/new")
    private String newFilm(){
        return "newFilm";
    }

    @PostMapping("/admin/new/confirm")
    private String newSuccess(String title, double rating, double ticketPrice,
                              String sessionTime, Model model){
        boolean success = adminService.newFilm(title, rating, ticketPrice, sessionTime);
        model.addAttribute("success", success);
        return "newConfirmation";
    }

}
