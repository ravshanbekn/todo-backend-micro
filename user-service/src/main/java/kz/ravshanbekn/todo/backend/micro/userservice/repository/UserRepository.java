package kz.ravshanbekn.todo.backend.micro.userservice.repository;

import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);
}
