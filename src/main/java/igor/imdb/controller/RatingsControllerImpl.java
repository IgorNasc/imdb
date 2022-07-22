package igor.imdb.controller;

import igor.imdb.business.RatingsService;
import igor.imdb.exception.movies.MovieNotFoundException;
import igor.imdb.exception.ratings.RatingOutOfRange;
import igor.imdb.exception.users.UserNotFoundException;
import igor.imdb.persistence.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/ratings")
public class RatingsControllerImpl {
    @Autowired
    private RatingsService ratingsService;

    @PatchMapping(value = "/rate")
    public ResponseEntity<String> rateMovie(@RequestHeader Integer userId, @RequestHeader Integer movieId, @RequestHeader Integer rate) {
        try {
            ratingsService.rateMovie(userId, movieId, rate);
            return ResponseEntity.ok("Success!");
        } catch (UserNotFoundException e) {
            return ResponseEntity.ok("User is not in the base!");
        } catch (MovieNotFoundException e) {
            return ResponseEntity.ok("Movie is not in the base!");
        } catch (RatingOutOfRange e) {
            return ResponseEntity.ok("Rating out of the range. Please set 1 to 5 (Integer)!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Ratings>> listRatings() {
        return ResponseEntity.ok(ratingsService.listRatings());
    }
}
