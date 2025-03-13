package kz.ravshanbekn.todo.backend.micro.userservice.service;

import kz.ravshanbekn.todo.backend.micro.userservice.converter.UserConverter;
import kz.ravshanbekn.todo.backend.micro.userservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User;
import kz.ravshanbekn.todo.backend.micro.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Transactional
    public UserDto createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = userConverter.toEntity(userCreateRequestDto);
        User savedUser = userRepository.save(user);
        return userConverter.toDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        User user = findUserById(userId);
        return userConverter.toDto(user);
    }

    @Transactional
    public UserDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = findUserById(userId);
        userConverter.update(user, userUpdateRequestDto);
        return userConverter.toDto(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found by ID: " + userId));
    }
}
