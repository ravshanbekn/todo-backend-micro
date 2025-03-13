package kz.ravshanbekn.todo.backend.micro.userservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.ErrorResponseDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user based on the provided data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
        return userService.createUser(userCreateRequestDto);
    }

    @Operation(
            summary = "Get user by id",
            description = "Returns user by provided id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User is found",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @Operation(
            summary = "Update user by id",
            description = "Updates user by provided ID and data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User is updated",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestParam Long userId,
                              @RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
        return userService.updateUser(userId, userUpdateRequestDto);
    }

    @Operation(
            summary = "Delete user by ID",
            description = "Deletes user by provided ID",
            parameters = {
                    @Parameter(name = "userId", description = "The ID of user to be deleted", example = "1", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "User was deleted"),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }
}
