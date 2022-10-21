package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public HttpEntity<HttpStatus> createUser() {
        User user = new User();
        user.setUsername("test");
        userRepository.save(user);

        return new HttpEntity<>(HttpStatus.OK);
    }

}
