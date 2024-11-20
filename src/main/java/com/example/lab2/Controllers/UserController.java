package com.example.lab2.Controllers;

import com.example.lab2.Repositories.Film;
import com.example.lab2.Repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class UserController {

    private final FilmRepository filmRepository;

    @Autowired
    public UserController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping("/loginUser")
    private String loginUser(Model model) {
        System.out.println("User logged in");
        List<Film> allFilms = filmRepository.findAll();
        model.addAttribute("allFilms", allFilms);
        return "userPage";
    }
}
