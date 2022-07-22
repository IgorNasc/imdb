package igor.imdb.persistence.repository;

import igor.imdb.persistence.model.Movies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Integer> {
    @Query("select m from Movies m WHERE m.name = :name")
    Optional<Movies> findByName(String name);

    @Query("select m from Movies m WHERE m.name like %:name%")
    List<Movies> findLikeName(String name);
}
