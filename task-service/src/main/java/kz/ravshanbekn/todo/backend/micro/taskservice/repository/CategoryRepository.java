package kz.ravshanbekn.todo.backend.micro.taskservice.repository;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUserIdOrderByTitleAsc(Long userId);

    @Query("SELECT c FROM Category c WHERE " +
            "(:title IS NULL OR :title = '' OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title,'%'))) " +
            "AND c.userId = :userId " +
            "ORDER BY c.title ASC")
    List<Category> findCategories(@Param("userId") Long userId, @Param("title") String title);
}
