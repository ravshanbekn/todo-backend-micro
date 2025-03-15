package kz.ravshanbekn.todo.backend.micro.taskservice.converter;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task.TaskUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    private final CategoryConverter categoryConverter;
    private final PriorityConverter priorityConverter;

    public Task toEntity(TaskCreateRequestDto taskCreateRequestDto) {
        Task task = new Task();
        task.setTitle(taskCreateRequestDto.getTitle());
        task.setCompleted(taskCreateRequestDto.isCompleted());
        task.setDate(taskCreateRequestDto.getDate());
        return task;
    }

    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDate(task.getDate());
        taskDto.setCompleted(task.getCompleted());
        taskDto.setUserId(task.getUserId());
        taskDto.setCategory(Objects.nonNull(task.getCategory()) ? categoryConverter.toDto(task.getCategory()) : null);
        taskDto.setPriority(Objects.nonNull(task.getPriority()) ? priorityConverter.toDto(task.getPriority()) : null);
        return taskDto;
    }

    public void update(TaskUpdateRequestDto taskUpdateRequestDto, Task task) {
        task.setTitle(taskUpdateRequestDto.getTitle());
        task.setCompleted(taskUpdateRequestDto.isCompleted());
        task.setDate(taskUpdateRequestDto.getDate());
    }
}
