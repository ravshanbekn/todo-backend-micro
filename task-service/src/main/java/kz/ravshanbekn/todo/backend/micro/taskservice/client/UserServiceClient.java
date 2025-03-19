package kz.ravshanbekn.todo.backend.micro.taskservice.client;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient {

    private final WebClient apiGatewayWebClient;

    // todo: if it's out of attempts, throws exception, make Global exception handler for it
    @Retryable(retryFor = WebClientResponseException.class, backoff = @Backoff(delay = 5, multiplier = 2))
    public Optional<UserDto> getUser(Long userId) {
        String uri = "/user?userId=" + userId;
        log.info("Calling user service: {}", uri);
        UserDto userDto = apiGatewayWebClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    throw new RuntimeException("Client error: " + response.statusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    throw new RuntimeException("Server error: " + response.statusCode());
                })
                .bodyToMono(UserDto.class)
                .block();
        log.info("User service returned: {}", userDto);
        return Optional.ofNullable(userDto);
    }
}
