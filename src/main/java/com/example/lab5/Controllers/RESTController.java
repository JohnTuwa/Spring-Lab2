package com.example.lab5.Controllers;

import com.example.lab5.Repositories.Film;
import com.example.lab5.Repositories.OracleFilmRepo;
import com.example.lab5.Sevices.RESTService;
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
    private final OracleFilmRepo oracleFilmRepo;

    @Autowired
    public RESTController(RESTService adminService, OracleFilmRepo oracleFilmRepo) {
        this.adminService = adminService;
        this.oracleFilmRepo = oracleFilmRepo;
    }

    @Operation(
            summary = "All films",
            description = "Returns list of all films")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Success")})
    @GetMapping("/films")
    private ResponseEntity<List<Film>> allFilms() {
        List<Film> films = oracleFilmRepo.findAll();
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
    private ResponseEntity<Film> filmById(@PathVariable long filmId) {
        Film film = oracleFilmRepo.getById(filmId);
        return ResponseEntity.ok(film);
    }

    @Operation(
            summary = "Add film",
            description = "Adding film to repository")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")})
    @PostMapping("/film/{filmId}")
    private ResponseEntity<String> addFilm(@PathVariable long filmId, @RequestBody Film film ){
        if(filmId != film.getFilmId()) {
            return ResponseEntity.badRequest().build();
        }
        adminService.createFilm(
                filmId,
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
    private ResponseEntity<String> editFilm(@PathVariable long filmId, @RequestBody Film film ){
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
    private ResponseEntity<String> removeFilm(@PathVariable long filmId){
        adminService.removeFilm(filmId);
        return ResponseEntity.ok("Film deleted successfully");
    }
}
