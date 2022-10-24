package agh.project.scs_forum.repository;

import agh.project.scs_forum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    List<Category> findByModeratedBy(String moderatedBy);

    boolean existsByName(String name);
}