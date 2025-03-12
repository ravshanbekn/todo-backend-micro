package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequestDto {

    @NotNull(message = "Category id should not be null")
    private Long id;

    @NotBlank(message = "Category name should not be blank")
    private String title;
}
