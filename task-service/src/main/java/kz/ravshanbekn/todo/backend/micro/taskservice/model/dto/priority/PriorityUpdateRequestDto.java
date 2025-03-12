package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityUpdateRequestDto {

    @NotNull(message = "Priority id should not be null")
    private Long id;

    @NotBlank(message = "Priority title should not be blank")
    private String title;

    @NotBlank(message = "Priority color should not be blank")
    private String color;
}
