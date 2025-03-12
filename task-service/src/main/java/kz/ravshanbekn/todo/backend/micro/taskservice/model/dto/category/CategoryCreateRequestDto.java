package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequestDto {

    @NotBlank(message = "Category name should not be blank")
    private String title;

    @Positive(message = "User ID should be positive")
    @NotNull(message = "User ID should not be null")
    private Long userId;
}
