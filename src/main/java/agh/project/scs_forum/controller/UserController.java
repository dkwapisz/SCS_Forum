package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import agh.project.scs_forum.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public HttpEntity<String> createUser(@RequestBody Map<String, String> userData) {
        String msg = userService.addUser(userData.get("username"), userData.get("password"));

        return new HttpEntity<>(msg);
    }

    // TODO add oldPassword and newPassword
    @PutMapping
    public HttpEntity<String> changePassword(@RequestBody Map<String, String> userData) {
        String msg = userService.changePassword(userData.get("username"), userData.get("password"));

        return new HttpEntity<>(msg);
    }

    @DeleteMapping
    public HttpEntity<String> deleteUser(@RequestBody Map<String, String> userData) {
        String msg = userService.deleteUser(userData.get("username"));

        return new HttpEntity<>(msg);
    }

}
