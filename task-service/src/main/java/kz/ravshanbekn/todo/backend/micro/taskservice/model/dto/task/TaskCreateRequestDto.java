package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateRequestDto {

    @NotBlank(message = "Title should not be blank")
    private String title;
    private boolean completed;
    private LocalDateTime date;
    private Long priorityId;
    private Long categoryId;
}
