package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryCreateRequestDto {

    @NotBlank(message = "Category name should not be blank")
    private String title;
}
