package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private Role role;
}