package com.example.lab2.Controllers;

import com.example.lab2.Repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final FilmRepository filmRepository;

    @Autowired
    public AdminController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @PostMapping("/admin")
    private String loginAdmin(){
        return "adminPage";
    }

    @PostMapping("/getById")
    private String getById(long filmId, Model model) {
        // Not final, just testing controller
        String title = filmRepository.getById(filmId).getTitle();
        model.addAttribute("title", title);
        return "tmp";
    }
}
