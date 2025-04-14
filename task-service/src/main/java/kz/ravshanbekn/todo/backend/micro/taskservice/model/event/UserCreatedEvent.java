package kz.ravshanbekn.todo.backend.micro.taskservice.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreatedEvent {

    private Long userId;
}