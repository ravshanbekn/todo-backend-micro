package kz.ravshanbekn.todo.backend.micro.taskservice.client;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class UserServiceClientFallback implements UserServiceClient {

    @Override
    public Optional<UserDto> getUser(Long userId) {
        log.info("Getting user from fallback - getUser: userId = {}", userId);
        return Optional.empty();
    }
}
