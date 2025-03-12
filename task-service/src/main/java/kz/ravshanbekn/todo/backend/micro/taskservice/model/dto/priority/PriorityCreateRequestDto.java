package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityCreateRequestDto {

    @NotBlank(message = "Priority title should not be blank")
    private String title;

    @NotBlank(message = "Priority color should not be blank")
    private String color;

    @Positive(message = "User ID should be positive")
    @NotNull(message = "User ID should not be null")
    private Long userId;
}
