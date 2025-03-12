package kz.ravshanbekn.todo.backend.micro.userservice.controller;


import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(UserCreateRequestDto userCreateRequestDto) {
        return userService.createUser(userCreateRequestDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
