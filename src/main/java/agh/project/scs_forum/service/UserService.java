package agh.project.scs_forum.service;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import agh.project.scs_forum.security.PasswordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordUtils passwordUtils;

    public UserService(UserRepository userRepository, PasswordUtils passwordUtils) {
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
    }

    public ResponseEntity<?> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User " + username + " not found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAllUsers() {
        List<User> usersList = userRepository.findAll();

        if (usersList.isEmpty()) {
            return new ResponseEntity<>("User table is empty.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addUser(User newUser) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return new ResponseEntity<>("User with that username already exists.", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordUtils.generateHash(newUser.getPassword()));
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public ResponseEntity<?> changePassword(String username, String oldPass, String newPass)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>("User " + username + " not found.", HttpStatus.NOT_FOUND);
        }

        if (!passwordUtils.validatePassword(oldPass, user.getPassword())) {
            return new ResponseEntity<>("Incorrect password.", HttpStatus.FORBIDDEN);
        }

        user.setPassword(passwordUtils.generateHash(newPass));
        user.setTempNewPassword(null);
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAllUsers() {
        userRepository.deleteAll();
        return new ResponseEntity<>("All users have been deleted.", HttpStatus.OK);
    }

}
