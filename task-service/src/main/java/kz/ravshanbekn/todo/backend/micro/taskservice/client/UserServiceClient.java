package kz.ravshanbekn.todo.backend.micro.taskservice.client;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "user-service", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    @GetMapping("/user")
    Optional<UserDto> getUser(@RequestParam Long userId);
}
