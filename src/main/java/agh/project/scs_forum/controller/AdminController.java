package agh.project.scs_forum.controller;

import agh.project.scs_forum.model.Admin;
import agh.project.scs_forum.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        List<Admin> adminList = adminService.getAllAdmins();
        return new ResponseEntity<>(adminList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> isAdmin(@PathVariable String username) {
        boolean isAdmin = adminService.isAdmin(username);
        return new ResponseEntity<>(isAdmin, HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> addAdmin(@PathVariable String username) {
        return adminService.addAdmin(username);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String username) {
        return adminService.deleteAdmin(username);
    }
}
