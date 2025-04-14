package kz.ravshanbekn.todo.backend.micro.userservice.config;

import kz.ravshanbekn.todo.backend.micro.userservice.producer.UserCreatedEvent;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Getter
@Configuration
public class ProducerConfig {

    private final Sinks.Many<Message<UserCreatedEvent>> userCreatedEventSink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(1024, false);

    @Bean
    public Supplier<Flux<Message<UserCreatedEvent>>> userCreatedEventSupplier() {
        return userCreatedEventSink::asFlux;
    }
}
