package kz.ravshanbekn.todo.backend.micro.userservice.service;


import kz.ravshanbekn.todo.backend.micro.userservice.converter.UserConverter;
import kz.ravshanbekn.todo.backend.micro.userservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User;
import kz.ravshanbekn.todo.backend.micro.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDto createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = userConverter.toEntity(userCreateRequestDto);
        User savedUser = userRepository.save(user);
        return userConverter.toDto(savedUser);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by ID: " + userId));
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found by email: " + email));
        return userConverter.toDto(user);
    }
}
