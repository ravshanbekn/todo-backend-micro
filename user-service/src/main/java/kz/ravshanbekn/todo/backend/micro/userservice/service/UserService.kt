package kz.ravshanbekn.todo.backend.micro.userservice.service

import kz.ravshanbekn.todo.backend.micro.userservice.converter.UserConverter
import kz.ravshanbekn.todo.backend.micro.userservice.exception.EntityNotFoundException
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserCreateRequestDto
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserUpdateRequestDto
import kz.ravshanbekn.todo.backend.micro.userservice.model.entity.User
import kz.ravshanbekn.todo.backend.micro.userservice.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserService(
    private val userRepository: UserRepository,
    private val userConverter: UserConverter
) {

    @Transactional
    open fun createUser(userCreateRequestDto: UserCreateRequestDto): UserDto {
        val user = userConverter.toEntity(userCreateRequestDto)
        val savedUser = userRepository.save(user)
        return userConverter.toDto(savedUser)
    }

    @Transactional(readOnly = true)
    open fun getUserById(userId: Long): UserDto {
        val user = findUserById(userId)
        return userConverter.toDto(user)
    }

    @Transactional
    open fun updateUser(userId: Long, userUpdateRequestDto: UserUpdateRequestDto): UserDto {
        val user = findUserById(userId)
        userConverter.update(user, userUpdateRequestDto)
        return userConverter.toDto(user)
    }

    @Transactional
    open fun deleteUser(userId: Long) {
        val user = findUserById(userId)
        userRepository.delete(user)
    }

    private fun findUserById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow {
                EntityNotFoundException(
                    "User not found by ID: $userId"
                )
            }
    }
}
