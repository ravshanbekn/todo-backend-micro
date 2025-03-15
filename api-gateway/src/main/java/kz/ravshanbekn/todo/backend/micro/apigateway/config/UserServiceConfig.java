package kz.ravshanbekn.todo.backend.micro.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserServiceConfig {

    @Bean
    public RouteLocator userProxyRouting(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/user/**")
                        .and().method("POST", "GET", "PUT", "DELETE")
                        .uri("lb://user-service"))
                .build();
    }
}