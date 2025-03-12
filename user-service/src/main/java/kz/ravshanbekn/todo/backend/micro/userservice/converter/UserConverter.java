package kz.ravshanbekn.todo.backend.micro.userservice.converter;

import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserCreateRequestDto userCreateRequestDto) {
        User user = new User();
        user.setEmail(userCreateRequestDto.getEmail());
        user.setName(userCreateRequestDto.getName());
        user.setPassword(userCreateRequestDto.getPassword());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        user.setRoles(user.getRoles());
        return userDto;
    }
}
