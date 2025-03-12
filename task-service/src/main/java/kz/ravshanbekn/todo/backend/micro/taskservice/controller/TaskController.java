package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskUpdateRequestDto;
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

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> findAll(@RequestParam Long userId) {
        return taskService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody @Valid TaskCreateRequestDto taskCreateRequestDto) {
        return taskService.create(taskCreateRequestDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskDto update(@RequestBody @Valid TaskUpdateRequestDto taskUpdateRequestDto) {
        return taskService.update(taskUpdateRequestDto);
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto findById(@PathVariable("taskId") Long taskId) {
        return taskService.findById(taskId);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<TaskDto> search(@RequestBody @Valid TaskFiltersDto taskFiltersDto) {
        return taskService.findTasksByFilters(taskFiltersDto);
    }
}
