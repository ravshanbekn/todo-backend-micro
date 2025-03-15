package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.TaskConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Task;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.TaskRepository;
import kz.ravshanbekn.todo.backend.micro.taskservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    public static final String ID_COLUMN = "id";

    private final TaskConverter taskConverter;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDto> findAllByUserId(Long userId) {
        List<Task> userTasks = taskRepository.findByUserIdOrderByTitleAsc(userId);
        return userTasks.stream().map(taskConverter::toDto).toList();
    }

    @Transactional
    public TaskDto create(Long userId, TaskCreateRequestDto taskCreateRequestDto) {
        Task task = taskConverter.toEntity(taskCreateRequestDto);
        task.setUserId(userId); // todo: verify if the user exits
        task.setCategory(Objects.nonNull(taskCreateRequestDto.getCategoryId()) ?
                categoryService.getCategoryById(taskCreateRequestDto.getCategoryId()) : null);
        task.setPriority(Objects.nonNull(taskCreateRequestDto.getPriorityId()) ?
                priorityService.getPriorityById(taskCreateRequestDto.getPriorityId()) : null);
        Task savedTask = taskRepository.save(task);
        return taskConverter.toDto(savedTask);
    }

    @Transactional
    public TaskDto update(TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = getTaskById(taskUpdateRequestDto.getId());
        task.setCategory(Objects.nonNull(taskUpdateRequestDto.getCategoryId()) ?
                categoryService.getCategoryById(taskUpdateRequestDto.getCategoryId()) : null);
        task.setPriority(Objects.nonNull(taskUpdateRequestDto.getPriorityId()) ?
                priorityService.getPriorityById(taskUpdateRequestDto.getPriorityId()) : null);
        taskConverter.update(taskUpdateRequestDto, task);
        Task updatedTask = taskRepository.save(task);
        return taskConverter.toDto(updatedTask);
    }

    @Transactional
    public void delete(Long taskId) {
        Task task = getTaskById(taskId);
        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public TaskDto findById(Long taskId) {
        // todo: check if the user is owner of this task
        Task task = getTaskById(taskId);
        return taskConverter.toDto(task);
    }

    @Transactional(readOnly = true)
    public Page<TaskDto> findTasksByFilters(Long userId, TaskFiltersDto taskFiltersDto) {
        // todo: check if the user exists

        Date dateFrom = null;
        Date dateTo = null;
        if (Objects.nonNull(taskFiltersDto.getDateFrom())) {
            dateFrom = DateUtil.getDateBeginning(taskFiltersDto.getDateFrom());
        }
        if (Objects.nonNull(taskFiltersDto.getDateTo())) {
            dateTo = DateUtil.getDateEnding(taskFiltersDto.getDateTo());
        }

        Sort.Direction direction = taskFiltersDto.getSortDirection().trim().equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, taskFiltersDto.getSortColumn(), ID_COLUMN);

        PageRequest pageRequest = PageRequest.of(taskFiltersDto.getPageNumber(), taskFiltersDto.getPageSize(), sort);

        Page<Task> result = taskRepository.findByParams(taskFiltersDto.getTitle(), taskFiltersDto.getCompleted(),
                taskFiltersDto.getPriorityId(), taskFiltersDto.getCategoryId(), userId, dateFrom, dateTo, pageRequest);
        return result.map(taskConverter::toDto);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found by ID: " + taskId));
    }
}
