package kz.ravshanbekn.todo.backend.micro.taskservice.repository;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdOrderByTitleAsc(Long userId);

    @Query("""
            SELECT t FROM Task t WHERE
            (:title IS NULL OR :title = '' OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title,'%'))) AND
            (:completed IS NULL OR t.completed = :completed) AND
            (:priorityId IS NULL OR t.priority.id = :priorityId) AND
            (:categoryId IS NULL OR t.category.id = :categoryId) AND
            (:categoryId IS NULL OR t.category.id = :categoryId) AND
            (
                (CAST(:dateFrom as timestamp) IS NULL OR t.date >= :dateFrom) AND
                (CAST(:dateTo as timestamp) IS NULL OR t.date <= :dateTo)
            ) AND
            (t.userId = :userId)
            """)
    Page<Task> findByParams(@Param("title") String title,
                            @Param("completed") Boolean completed,
                            @Param("priorityId") Long priorityId,
                            @Param("categoryId") Long categoryId,
                            @Param("userId") Long userId,
                            @Param("dateFrom") Date dateFrom,
                            @Param("dateTo") Date dateTo,
                            Pageable pageable);
}
