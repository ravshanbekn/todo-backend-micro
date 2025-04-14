package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.enums.Verify;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/init")
    @ResponseStatus(HttpStatus.CREATED)
    public void initTasks(@RequestParam Long userId) {
        taskService.createInitTasks(userId);
    }

    @Operation(
            summary = "Retrieve all tasks for a user",
            description = "Returns a list of all tasks associated with the given user ID.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid user ID")
            }
    )
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> findAll(@RequestParam Long userId) {
        return taskService.findAllByUserId(userId);
    }

    @Operation(
            summary = "Create a new task",
            description = "Creates a new task for the specified user ID.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Task successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestParam Long userId,
                          @RequestBody @Valid TaskCreateRequestDto taskCreateRequestDto) {
        return taskService.create(userId, taskCreateRequestDto, Verify.ENABLED);
    }

    @Operation(
            summary = "Update an existing task",
            description = "Updates an existing task with new details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskDto update(@RequestParam Long taskId,
                          @RequestBody @Valid TaskUpdateRequestDto taskUpdateRequestDto) {
        return taskService.update(taskId, taskUpdateRequestDto);
    }

    @Operation(
            summary = "Delete a task by ID",
            description = "Deletes a task based on the provided task ID.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID of the task to be deleted", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid task ID"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
    }

    @Operation(
            summary = "Get task by ID",
            description = "Retrieves a task based on the provided task ID.",
            parameters = {
                    @Parameter(name = "taskId", description = "ID of the task to retrieve", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Task successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid task ID"),
                    @ApiResponse(responseCode = "404", description = "Task not found")
            }
    )
    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto findById(@PathVariable("taskId") Long taskId) {
        return taskService.findById(taskId);
    }

    @Operation(
            summary = "Search tasks with filters",
            description = "Searches for tasks based on the provided filter criteria for a specific user.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskDto> search(@RequestParam Long userId,
                                @RequestBody @Valid TaskFiltersDto taskFiltersDto) {
        return taskService.findTasksByFilters(userId, taskFiltersDto);
    }
}
