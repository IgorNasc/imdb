package igor.imdb.business;

import igor.imdb.exception.movies.MovieAlreadyExists;
import igor.imdb.exception.movies.MovieNotFoundException;
import igor.imdb.persistence.model.Movies;
import igor.imdb.persistence.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MoviesService {
    @Autowired
    private MoviesRepository moviesRepository;

    public Movies findByCode(Integer movieId) {
        return moviesRepository.findById(movieId).orElseThrow(MovieNotFoundException::new);
    }

    public List<Movies> findLikeName(String name) {
        return moviesRepository.findLikeName(name);
    }

    public List<Movies> listMovies() {
        return StreamSupport.stream(moviesRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createMovie(Movies movies) {
        if (moviesRepository.findByName(movies.getName()).isPresent()) {
            throw new MovieAlreadyExists();
        }

        moviesRepository.save(movies);
    }

    public void deleteMovie(Integer movieId) {
        moviesRepository.deleteById(movieId);
    }

    public Movies updateRating(Double avgRating, Movies movie) {
        movie.setAvgRating(avgRating);
        return moviesRepository.save(movie);
    }
}
