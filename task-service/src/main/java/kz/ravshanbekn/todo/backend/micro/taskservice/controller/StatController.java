package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.StatDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController("/stat")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @Operation(
            summary = "Get statistics by user ID",
            description = "Retrieves statistics for a user based on their unique ID.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "123")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistics successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid user ID"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.OK)
    public StatDto findByUserID(@RequestParam Long userId) {
        return statService.findByUserId(userId);
    }
}
