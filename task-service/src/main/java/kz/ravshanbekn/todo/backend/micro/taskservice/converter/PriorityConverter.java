package kz.ravshanbekn.todo.backend.micro.taskservice.converter;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Priority;
import org.springframework.stereotype.Component;

@Component
public class PriorityConverter {

    public Priority toEntity(PriorityCreateRequestDto priorityCreateRequestDto) {
        Priority priority = new Priority();
        priority.setTitle(priorityCreateRequestDto.getTitle());
        priority.setColor(priorityCreateRequestDto.getColor());
        priority.setUserId(priorityCreateRequestDto.getUserId());
        return priority;
    }

    public PriorityDto toDto(Priority priority) {
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(priority.getId());
        priorityDto.setTitle(priority.getTitle());
        priorityDto.setColor(priority.getColor());
        priorityDto.setUserId(priorityDto.getUserId());
        return priorityDto;
    }

    public void update(PriorityUpdateRequestDto priorityUpdateRequestDto, Priority priority) {
        priority.setTitle(priorityUpdateRequestDto.getTitle());
        priority.setColor(priorityUpdateRequestDto.getColor());
    }
}
