package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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

    @PostMapping("/all")
    public List<PriorityDto> findAll(@RequestParam Long userId) {
        return priorityService.findAll(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriorityDto add(@RequestBody @Valid PriorityCreateRequestDto priorityCreateRequestDto) {
        return priorityService.create(priorityCreateRequestDto);
    }

    @GetMapping("/{priorityId}")
    @ResponseStatus(HttpStatus.OK)
    public PriorityDto findById(@PathVariable("priorityId") Long priorityId) {
        return priorityService.findById(priorityId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PriorityDto update(@RequestBody @Valid PriorityUpdateRequestDto priorityUpdateRequestDto) {
        return priorityService.update(priorityUpdateRequestDto);
    }

    @DeleteMapping("/{priorityId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("priorityId") Long priorityId) {
        priorityService.deleteById(priorityId);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<PriorityDto> search(@RequestBody PriorityFiltersDto priorityFiltersDto) {
        return priorityService.searchByFilter(priorityFiltersDto);
    }
}
