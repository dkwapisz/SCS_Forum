package agh.project.scs_forum.repository;

import agh.project.scs_forum.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByUsername(String username);

    Optional<Admin> findAdminByUsername(String username);
}