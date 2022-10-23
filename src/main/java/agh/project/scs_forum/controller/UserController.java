package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import agh.project.scs_forum.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.addUser(newUser);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody User givenUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.changePassword(givenUser.getUsername(), givenUser.getPassword(), givenUser.getTempNewPassword());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

}
