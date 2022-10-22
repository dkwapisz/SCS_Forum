package agh.project.scs_forum.service;

import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return "nie poszlo smutno";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return "poszlo<3";
    }

    public String changePassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "nie poszlo :(";
        }
        user.setPassword(password);
        userRepository.save(user);
        return "poszlo";
    }

    public String deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "nie poszlo";
        }
        userRepository.delete(user);
        return "poszlo";
    }

}
