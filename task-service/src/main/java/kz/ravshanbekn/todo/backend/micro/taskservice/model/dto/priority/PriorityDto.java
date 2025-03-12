package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityDto {

    private Long id;
    private String title;
    private String color;
    private Long userId;
}
