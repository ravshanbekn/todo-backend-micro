package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskUpdateRequestDto {

    @NotNull(message = "Task id should not be null")
    private Long id;
    @NotBlank(message = "Title should not be blank")
    private String title;
    private boolean completed;
    private Date date;
    private Long priorityId;
    private Long categoryId;
}
