package igor.imdb.controller;

import igor.imdb.business.UsersService;
import igor.imdb.exception.users.UserNotFoundException;
import igor.imdb.persistence.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UsersControllerImpl {
    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        try {
            usersService.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Users> loginUser(@RequestHeader String username, @RequestHeader String password) {
        try {
            return ResponseEntity.ok(usersService.login(username, password));
        } catch (UserNotFoundException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> loginUser(@RequestHeader Integer userId) {
        try {
            usersService.deleteUser(userId);
            return ResponseEntity.ok("User deleted!");
        }  catch (EmptyResultDataAccessException e){
            return ResponseEntity.ok("User is not in the base!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}