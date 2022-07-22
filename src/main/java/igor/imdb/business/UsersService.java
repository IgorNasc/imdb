package igor.imdb.business;

import igor.imdb.exception.users.UserNotFoundException;
import igor.imdb.persistence.model.Users;
import igor.imdb.persistence.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public void registerUser(Users user) {
        usersRepository.save(user);
    }

    public Users findByCode(Integer userId) {
        return usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    public Users login(String username, String password) {
        var user = usersRepository.findByUsernameAndPassword(username, password);

        if (user.isPresent()) {
           return user.get();
        }

        throw new UserNotFoundException();
    }
}
