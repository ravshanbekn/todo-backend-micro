package kz.ravshanbekn.todo.backend.micro.userservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskServiceClient {

    private final WebClient apiGatewayWebClient;

    // todo: if it's out of attempts, throws exception, make Global exception handler for it
    @Retryable(retryFor = WebClientResponseException.class, backoff = @Backoff(delay = 5, multiplier = 2))
    public void addInitTasks(Long userId) {
        String uri = "/task/init?userId=" + userId;
        log.info("Calling task service for init service: {}", uri);
        apiGatewayWebClient.post()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    throw new RuntimeException("Client error: " + response.statusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    throw new RuntimeException("Server error: " + response.statusCode());
                })
                .bodyToMono(Void.class)
                .block();
        log.info("Task service calling is finished");
    }
}
