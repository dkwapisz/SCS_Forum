package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import agh.project.scs_forum.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO fix RequestBody
    @PostMapping("/add")
    public HttpEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
        String msg = userService.addUser(username, password);

        return new HttpEntity<>(msg);
    }

    @PutMapping("/password")
    public HttpEntity<String> changePassword(@RequestParam String username, @RequestParam String password) {
        String msg = userService.changePassword(username, password);

        return new HttpEntity<>(msg);
    }

    @DeleteMapping("/delete")
    public HttpEntity<String> deleteUser(@RequestParam String username) {
        String msg = userService.deleteUser(username);

        return new HttpEntity<>(msg);
    }

}
