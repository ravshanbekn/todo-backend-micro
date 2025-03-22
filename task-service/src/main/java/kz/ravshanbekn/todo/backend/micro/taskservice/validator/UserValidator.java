package kz.ravshanbekn.todo.backend.micro.taskservice.validator;

import kz.ravshanbekn.todo.backend.micro.taskservice.client.UserServiceClient;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidator {

    private final UserServiceClient userServiceClient;

    public void validateUserExistence(Long userId) {
        log.info("Validating user existence with id {}", userId);
        userServiceClient.getUser(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " does not exist"));
    }
}
