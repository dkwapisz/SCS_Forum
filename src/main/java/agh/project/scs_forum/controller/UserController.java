package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import agh.project.scs_forum.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public HttpEntity<String> createUser(@RequestBody String username, @RequestBody String password) {
        String msg = userService.addUser(username,password);

        return new HttpEntity<>(msg);
    }

    @PostMapping("/delete")
    public HttpEntity<HttpStatus> deleteUser() {

        return null;
    }

}
