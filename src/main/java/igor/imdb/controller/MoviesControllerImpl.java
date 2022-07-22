package igor.imdb.controller;

import igor.imdb.business.MoviesService;
import igor.imdb.exception.movies.MovieAlreadyExists;
import igor.imdb.exception.movies.MovieNotFoundException;
import igor.imdb.persistence.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MoviesControllerImpl {
    @Autowired
    private MoviesService moviesService;

    @GetMapping(value = "/find/id")
    public ResponseEntity<Movies> getMovieByCode(@RequestHeader Integer movieId) {
        try {
            return ResponseEntity.ok(moviesService.findByCode(movieId));
        } catch (MovieNotFoundException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/find/name")
    public ResponseEntity<List<Movies>> getMovieByName(@RequestHeader String name) {
        try {
            return ResponseEntity.ok(moviesService.findLikeName(name));
        } catch (MovieNotFoundException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Movies>> listMovies() {
        try {
            return ResponseEntity.ok(moviesService.listMovies());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createMovie(@RequestBody Movies movies) {
        try {
            moviesService.createMovie(movies);
            return ResponseEntity.ok("Movie added to the base!");
        } catch (MovieAlreadyExists e) {
            return ResponseEntity.ok("Movie already exists in the base!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteMovie(@RequestHeader Integer movieId) {
        try {
            moviesService.deleteMovie(movieId);
            return ResponseEntity.ok("Movie deleted!");
        }  catch (EmptyResultDataAccessException e){
            return ResponseEntity.ok("Movie is not in the base!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
