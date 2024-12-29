package com.example.lab4.Controllers;

import com.example.lab4.Repositories.FilmRepository;
import com.example.lab4.Repositories.Film;
import com.example.lab4.Sevices.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final FilmRepository filmRepository;
    private final UserService userService;

    @Autowired
    public UserController(FilmRepository filmRepository, UserService userService) {
        this.filmRepository = filmRepository;
        this.userService = userService;
    }

    @Operation(
            summary = "Get seats",
            description = "Get seats of the film by identifier",
            parameters = {@Parameter(name = "filmId", description = "id of the film", example = "2")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/{filmId}")
    private ResponseEntity<boolean[][]> getFilmSeats(@PathVariable long filmId) {
        Film film = filmRepository.getById(filmId);
        if (film == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(film.getSeats());
    }

    @Operation(
            summary = "If seat is taken",
            description = "Check if the seat is already taken",
            parameters = {
                    @Parameter(name = "filmId", description = "id of the film", example = "2"),
                    @Parameter(name = "row", description = "row of the chosen seat", example = "0"),
                    @Parameter(name = "col", description = "column of the chosen seat", example = "0")}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping("/film/{filmId}/seat")
    private ResponseEntity<String> getSeat(@PathVariable long filmId,
                                           @RequestParam int row,
                                           @RequestParam int col) {
        Film film = filmRepository.getById(filmId);
        if (film == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film not found");
        }
        if (userService.isSeatTaken(row, col, filmId)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat is already taken");
        }
        return ResponseEntity.ok("Seat bought successfully");
    }
}
