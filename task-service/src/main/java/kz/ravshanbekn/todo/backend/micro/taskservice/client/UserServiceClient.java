package kz.ravshanbekn.todo.backend.micro.taskservice.client;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.external.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    @Value("${api-gateway.host}")
    private String apiGateWayHost;
    @Value("${api-gateway.port}")
    private String apiGateWayPort;

    private final RestTemplate restTemplate;

    @Retryable(retryFor = RestClientException.class, backoff = @Backoff(delay = 5, multiplier = 2))
    public Optional<UserDto> getUser(Long userId) {
        String url = getBaseUrl() + "/users/" + userId;
        ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Optional.ofNullable(response.getBody());
        }
        return Optional.empty();
    }

    private String getBaseUrl() {
        return apiGateWayHost + ":" + apiGateWayPort;
    }
}
