package igor.imdb.persistence.repository;

import igor.imdb.persistence.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Query("select u from Users u where u.username = :username and u.password = :password")
    Optional<Users> findByUsernameAndPassword(String username, String password);
}
