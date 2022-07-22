package igor.imdb.persistence.repository;

import igor.imdb.persistence.model.Ratings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends CrudRepository<Ratings, Integer> {
    @Query("select avg(rating) from Ratings r where r.movie.id = :movieId")
    Double avgRatingByMovie(Integer movieId);
}
