package com.example.lab4.Controllers;

import com.example.lab4.Repositories.Film;
import com.example.lab4.Repositories.FilmRepository;
import com.example.lab4.Sevices.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final FilmRepository filmRepository;
    private final AdminService adminService;

    @Autowired
    public AdminController(FilmRepository filmRepository, AdminService adminService) {
        this.filmRepository = filmRepository;
        this.adminService = adminService;
    }

    @Operation(
            summary = "All films",
            description = "Returns list of all films")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping("/films")
    private ResponseEntity<List<Film>> allFilms() {
        List<Film> films = filmRepository.findAll();
        return ResponseEntity.ok(films);
    }

    @Operation(
            summary = "Film by id",
            description = "Gets a film by identifier",
            parameters = {
                    @Parameter(name = "filmId", description = "id of the film", example = "2")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/{filmId}")
    private ResponseEntity<Film> allFilms(@PathVariable long filmId) {
        Film film = filmRepository.getById(filmId);
        if (film == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(film);
    }

    @Operation(
            summary = "Add film",
            description = "Adding film to repository")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping("/add")
    private ResponseEntity<String> addFilm(@RequestBody Film film ){

        adminService.newFilm(
                film.getTitle(),
                film.getRating(),
                film.getTicketPrice(),
                film.getSessionTime());

        return ResponseEntity.ok("Film added successfully");
    }

    @Operation(
            summary = "Edit film",
            description = "Editing existing film",
            parameters = {
                    @Parameter(name = "filmId", description = "id of the film", example = "2")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping("/edit/{filmId}")
    private ResponseEntity<String> editFilm(@PathVariable long filmId, @RequestBody Film film ){
        if(filmId != film.getId()){
            return ResponseEntity.badRequest().build();
        }
        if (filmRepository.getById(filmId) == null) {
            return ResponseEntity.notFound().build();
        }
        adminService.editFilm(filmId,
                film.getTitle(),
                film.getRating(),
                film.getTicketPrice(),
                film.getSessionTime());

        return ResponseEntity.ok("Film edited successfully");
    }

    @Operation(
            summary = "Delete film",
            description = "Deleting existing film",
            parameters = {
                    @Parameter(name = "filmId", description = "id of the film", example = "2")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @DeleteMapping("/remove/{filmId}")
    private ResponseEntity<String> removeFilm(@PathVariable long filmId){
        if(filmRepository.getById(filmId) == null){
            return ResponseEntity.notFound().build();
        }
        adminService.removeFilm(filmId);
        return ResponseEntity.ok("Film deleted successfully");
    }
}
