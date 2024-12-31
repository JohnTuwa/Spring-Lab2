package com.example.lab6.Controllers;

import com.example.lab6.Entity.Film;
import com.example.lab6.Sevices.RESTService;
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
public class RESTController {

    private final RESTService adminService;

    @Autowired
    public RESTController(RESTService adminService) {
        this.adminService = adminService;
    }

    @Operation(
            summary = "All films",
            description = "Returns list of all films")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping("/films")
    private ResponseEntity<List<Film>> allFilms() {
        List<Film> films = adminService.listAllFilms();
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
    private ResponseEntity<Film> filmById(@PathVariable int filmId) {
        Film film = adminService.getFilmById(filmId);
        return ResponseEntity.ok(film);
    }

    @Operation(
            summary = "Film by title",
            description = "Gets a film by title",
            parameters = {
                    @Parameter(name = "title", description = "title of the film", example = "Inception")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/title/{title}")
    private ResponseEntity<Film> filmByTitle(@PathVariable String title) {
        Film film = adminService.getFilmByTitle(title);
        return ResponseEntity.ok(film);
    }

    @Operation(
            summary = "Film by price",
            description = "Gets all films with price less than specified",
            parameters = {
                    @Parameter(name = "maxPrice", description = "Maximum price of the film", example = "12.10")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/price/{maxPrice}")
    private ResponseEntity<List<Film>> filmByPrice(@PathVariable double maxPrice) {
        List<Film> films = adminService.getFilmByPriceLessThan(maxPrice);
        return ResponseEntity.ok(films);
    }

    @Operation(
            summary = "Film above rating",
            description = "Gets a film with rating higher then specified",
            parameters = {
                    @Parameter(name = "rating", description = "rating of the films", example = "8.8")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/rating/{rating}")
    private ResponseEntity<List<Film>> filmByRating(@PathVariable double rating) {
        List<Film> films = adminService.getFilmAboveRating(rating);
        return ResponseEntity.ok(films);
    }

    @Operation(
            summary = "Add film",
            description = "Adding film to repository")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping("/film")
    private ResponseEntity<String> addFilm( @RequestBody Film film ){
        adminService.createFilm(
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
    @PutMapping("/film/{filmId}")
    private ResponseEntity<String> editFilm(@PathVariable int filmId, @RequestBody Film film ){
        if(filmId != film.getFilmId()){
            return ResponseEntity.badRequest().build();
        }
        adminService.updateFilm(
                filmId,
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
    @DeleteMapping("/film/{filmId}")
    private ResponseEntity<String> removeFilm(@PathVariable int filmId){
        adminService.removeFilm(filmId);
        return ResponseEntity.ok("Film deleted successfully");
    }
}
