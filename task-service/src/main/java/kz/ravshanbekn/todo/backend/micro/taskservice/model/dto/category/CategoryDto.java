package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;
    private Long userId;
}
