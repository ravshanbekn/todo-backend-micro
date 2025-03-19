package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityService priorityService;

    @Operation(
            summary = "Retrieve all priorities for a user",
            description = "Returns a list of all priorities associated with the given user ID.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Priorities successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PriorityDto> findAll(@RequestParam Long userId) {
        return priorityService.findAll(userId);
    }

    @Operation(
            summary = "Create a new priority",
            description = "Creates a new priority for the specified user.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Priority successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriorityDto add(@RequestParam Long userId,
                           @RequestBody @Valid PriorityCreateRequestDto priorityCreateRequestDto) {
        return priorityService.create(userId, priorityCreateRequestDto);
    }

    @Operation(
            summary = "Get priority by ID",
            description = "Retrieves a priority by its unique ID.",
            parameters = {
                    @Parameter(name = "priorityId", description = "Priority ID", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Priority successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid priority ID"),
                    @ApiResponse(responseCode = "404", description = "Priority not found")
            }
    )
    @GetMapping("/{priorityId}")
    @ResponseStatus(HttpStatus.OK)
    public PriorityDto findById(@PathVariable("priorityId") Long priorityId) {
        return priorityService.findById(priorityId);
    }

    @Operation(
            summary = "Update an existing priority",
            description = "Updates an existing priority with new data.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Priority successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "Priority not found")
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PriorityDto update(@RequestParam Long priorityId,
                              @RequestBody @Valid PriorityUpdateRequestDto priorityUpdateRequestDto) {
        return priorityService.update(priorityId, priorityUpdateRequestDto);
    }

    @Operation(
            summary = "Delete priority by ID",
            description = "Deletes a priority by its unique ID.",
            parameters = {
                    @Parameter(name = "priorityId", description = "Priority ID to delete", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Priority successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid priority ID"),
                    @ApiResponse(responseCode = "404", description = "Priority not found")
            }
    )
    @DeleteMapping("/{priorityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("priorityId") Long priorityId) {
        priorityService.deleteById(priorityId);
    }

    @Operation(
            summary = "Search priorities by filters",
            description = "Searches priorities based on user ID and additional filter criteria.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "123")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Priorities successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PriorityDto> search(@RequestParam Long userId, @RequestBody PriorityFiltersDto priorityFiltersDto) {
        return priorityService.searchByFilter(userId, priorityFiltersDto);
    }
}
