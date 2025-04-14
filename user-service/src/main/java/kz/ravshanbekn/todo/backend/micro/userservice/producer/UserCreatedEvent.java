package kz.ravshanbekn.todo.backend.micro.userservice.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreatedEvent {

    private Long userId;
}
