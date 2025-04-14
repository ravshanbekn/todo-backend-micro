package kz.ravshanbekn.todo.backend.micro.userservice.producer;

import kz.ravshanbekn.todo.backend.micro.userservice.config.ProducerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedEventProducer {

    private final ProducerConfig producerConfig;

    public void sendUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        producerConfig.getUserCreatedEventSink()
                .emitNext(MessageBuilder.withPayload(userCreatedEvent).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        log.info("User created event: {} sent", userCreatedEvent);
    }
}
