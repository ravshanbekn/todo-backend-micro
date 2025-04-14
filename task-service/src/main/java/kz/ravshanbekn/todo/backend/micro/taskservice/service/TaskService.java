package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.TaskConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Task;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.enums.Verify;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.TaskRepository;
import kz.ravshanbekn.todo.backend.micro.taskservice.util.DateUtil;
import kz.ravshanbekn.todo.backend.micro.taskservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    public static final String ID_COLUMN = "id";

    private final TaskConverter taskConverter;
    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final TaskRepository taskRepository;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public List<TaskDto> findAllByUserId(Long userId) {
        List<Task> userTasks = taskRepository.findByUserIdOrderByTitleAsc(userId);
        return userTasks.stream().map(taskConverter::toDto).toList();
    }

    @Transactional
    public TaskDto create(Long userId, TaskCreateRequestDto taskCreateRequestDto, Verify verify) {
        if (verify.equals(Verify.ENABLED)) {
            userValidator.validateUserExistence(userId); // todo: change to jwt
        }
        Task task = taskConverter.toEntity(taskCreateRequestDto);
        task.setUserId(userId);
        task.setCategory(Objects.nonNull(taskCreateRequestDto.getCategoryId()) ?
                categoryService.getCategoryById(taskCreateRequestDto.getCategoryId()) : null);
        task.setPriority(Objects.nonNull(taskCreateRequestDto.getPriorityId()) ?
                priorityService.getPriorityById(taskCreateRequestDto.getPriorityId()) : null);
        Task savedTask = taskRepository.save(task);
        return taskConverter.toDto(savedTask);
    }

    @Transactional
    public TaskDto update(Long taskId, TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = getTaskById(taskId);
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

        LocalDateTime dateFrom = null;
        LocalDateTime dateTo = null;
        if (Objects.nonNull(taskFiltersDto.getDateFrom())) {
            dateFrom = DateUtil.getStartOfDay(taskFiltersDto.getDateFrom());
        }
        if (Objects.nonNull(taskFiltersDto.getDateTo())) {
            dateTo = DateUtil.getEndOfDay(taskFiltersDto.getDateTo());
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

    @Transactional
    public void createInitTasks(Long userId) {
        PriorityCreateRequestDto highPriority = new PriorityCreateRequestDto("High", "#FF5768");
        PriorityCreateRequestDto mediumPriority = new PriorityCreateRequestDto("Medium", "#FFD872");
        PriorityCreateRequestDto lowPriority = new PriorityCreateRequestDto("Low", "#FFD872");

        CategoryCreateRequestDto workCategory = new CategoryCreateRequestDto("Work");
        CategoryCreateRequestDto familyCategory = new CategoryCreateRequestDto("Family");

        PriorityDto createdHighPriority = priorityService.create(userId, highPriority);
        PriorityDto createdMediumPriority = priorityService.create(userId, mediumPriority);
        priorityService.create(userId, lowPriority);

        CategoryDto createdWorkCategory = categoryService.create(userId, workCategory);
        CategoryDto createdFamilyCategory = categoryService.create(userId, familyCategory);

        TaskCreateRequestDto callFamilyTask = TaskCreateRequestDto.builder()
                .date(LocalDateTime.now().plusDays(1))
                .title("Call Family")
                .completed(false)
                .categoryId(createdFamilyCategory.getId())
                .priorityId(createdHighPriority.getId())
                .build();

        TaskCreateRequestDto accomplishWorkTask = TaskCreateRequestDto.builder()
                .date(LocalDateTime.now().plusDays(2))
                .title("Accomplish Work")
                .completed(false)
                .categoryId(createdWorkCategory.getId())
                .priorityId(createdMediumPriority.getId())
                .build();

        create(userId, callFamilyTask, Verify.DISABLED);
        create(userId, accomplishWorkTask, Verify.DISABLED);

        log.info("User init tasks were created");
    }
}
