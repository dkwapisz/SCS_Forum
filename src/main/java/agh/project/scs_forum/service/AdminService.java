package agh.project.scs_forum.service;

import agh.project.scs_forum.model.Admin;
import agh.project.scs_forum.model.User;
import agh.project.scs_forum.repository.AdminRepository;
import agh.project.scs_forum.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    AdminRepository adminRepository;
    UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;

        initializeTestAdmin();
    }

    private void initializeTestAdmin() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        Admin admin = new Admin();
        admin.setUsername("test");
        admin.setAddedBy("test");
        userRepository.save(user);
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public boolean isAdmin(String username) {
        return adminRepository.existsByUsername(username);
    }

    public ResponseEntity<?> addAdmin(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("User " + username + " not found", HttpStatus.NOT_FOUND);
        } else if (adminRepository.existsByUsername(username)) {
            return new ResponseEntity<>("User " + username + " is already admin", HttpStatus.CONFLICT);
        } else {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setAddedBy("x"); // TODO Get username from session
            adminRepository.save(admin);
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteAdmin(String username) {
        Optional<Admin> admin = adminRepository.findAdminByUsername(username);

        if (admin.isPresent()) {
            adminRepository.delete(admin.get());
            return new ResponseEntity<>("User " + username + " deleted successfully", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("User " + username + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
