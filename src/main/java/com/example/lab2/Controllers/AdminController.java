package com.example.lab2.Controllers;

import com.example.lab2.Repositories.FilmRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/getById")
    private String getById(long id, Model model) {
        // Not final, just testing controller
        FilmRepository filmRepository = new FilmRepository();
        filmRepository.addFilm(1, "Inception", 9.0, 200, "120");
        filmRepository.addFilm(2, "Dunkirk", 9.0, 200, "120");
        String title = filmRepository.getById(id);
        model.addAttribute("title", title);
        return "tmp";
    }
}
