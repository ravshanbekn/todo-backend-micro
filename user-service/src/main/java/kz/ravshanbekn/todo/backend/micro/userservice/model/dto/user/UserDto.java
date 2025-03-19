package kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user;

import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private Role role;
}
