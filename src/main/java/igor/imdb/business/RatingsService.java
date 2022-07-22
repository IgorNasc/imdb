package igor.imdb.business;

import igor.imdb.exception.ratings.RatingOutOfRange;
import igor.imdb.persistence.model.Movies;
import igor.imdb.persistence.model.Ratings;
import igor.imdb.persistence.model.Users;
import igor.imdb.persistence.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RatingsService {
    @Autowired
    private RatingsRepository ratingsRepository;
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private UsersService usersService;

    public void rateMovie(Integer userId, Integer movieId, Integer rate) {
        if (rate < 1 || rate > 5) {
            throw new RatingOutOfRange();
        }

        var user = usersService.findByCode(userId);
        var movie = moviesService.findByCode(movieId);

        ratingsRepository.save(Ratings.builder().movie(movie).user(user).rating(rate).build());

        var avgRating = ratingsRepository.avgRatingByMovie(movie.getId());

        moviesService.updateRating(avgRating, movie);
    }

    public List<Ratings> listRatings() {
        return StreamSupport.stream(ratingsRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
