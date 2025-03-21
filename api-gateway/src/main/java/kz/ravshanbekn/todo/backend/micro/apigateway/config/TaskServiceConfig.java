package kz.ravshanbekn.todo.backend.micro.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServiceConfig {

    @Bean
    public RouteLocator taskProxyRouting(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("task-service", r -> r.path("/task/**", "/category/**", "/priority/**", "/stat/**")
                        .and().method("POST", "GET", "PUT", "DELETE")
                        .uri("lb://task-service"))
                .build();
    }
}
