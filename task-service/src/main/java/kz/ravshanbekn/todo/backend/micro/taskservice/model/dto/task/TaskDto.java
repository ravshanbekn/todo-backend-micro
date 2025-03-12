package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TaskDto {

    private Long id;
    private String title;
    private Boolean completed;
    private Date date;
    private PriorityDto priority;
    private CategoryDto category;
    private Long userId;
}
