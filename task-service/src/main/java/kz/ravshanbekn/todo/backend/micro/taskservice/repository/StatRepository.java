package kz.ravshanbekn.todo.backend.micro.taskservice.repository;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {

    Optional<Stat> findByUserId(Long userId);
}
