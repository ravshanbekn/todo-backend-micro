package kz.ravshanbekn.todo.backend.micro.userservice.converter;

import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.Role;
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public User toEntity(UserCreateRequestDto userCreateRequestDto) {
        User user = new User();
        user.setEmail(userCreateRequestDto.getEmail());
        user.setName(userCreateRequestDto.getName());
        user.setPassword(userCreateRequestDto.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRole());
        return userDto;
    }

    public void update(User user, UserUpdateRequestDto userUpdateRequestDto) {
        user.setEmail(userUpdateRequestDto.getEmail());
        user.setName(userUpdateRequestDto.getName());
        user.setPassword(userUpdateRequestDto.getPassword());
    }
}
