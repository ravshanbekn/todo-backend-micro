package kz.ravshanbekn.todo.backend.micro.taskservice.repository;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    List<Priority> findByUserIdOrderByIdAsc(Long userId);

    @Query("SELECT p FROM Priority p WHERE " +
            "(:title IS NULL OR :title='' OR lower(p.title) LIKE LOWER(CONCAT('%', :title,'%'))) " +
            "AND p.userId = :userId " +
            "ORDER BY p.title ASC")
    List<Priority> findPriorities(@Param("userId") Long userId, @Param("title") String title);
}
