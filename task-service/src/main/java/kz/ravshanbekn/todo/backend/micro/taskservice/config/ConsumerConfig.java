package kz.ravshanbekn.todo.backend.micro.taskservice.config;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.event.UserCreatedEvent;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

    private final TaskService taskService;

    @Bean
    public Consumer<Message<UserCreatedEvent>> userCreatedEventConsumer() {
        return message -> taskService.createInitTasks(message.getPayload().getUserId());
    }
}
