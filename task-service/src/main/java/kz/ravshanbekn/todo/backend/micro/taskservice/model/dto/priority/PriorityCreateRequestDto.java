package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriorityCreateRequestDto {

    @NotBlank(message = "Priority title should not be blank")
    private String title;

    @NotBlank(message = "Priority color should not be blank")
    private String color;
}
