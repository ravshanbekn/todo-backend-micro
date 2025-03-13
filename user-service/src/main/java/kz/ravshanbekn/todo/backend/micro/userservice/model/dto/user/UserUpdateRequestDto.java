package kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to update User")
public class UserUpdateRequestDto {

    @Schema(example = "newemailofjohn@example.com")
    @Email(message = "Should have email structure")
    @NotBlank(message = "Should not be blank")
    private String email;

    @Schema(example = "NewNameOfJohn")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @Schema(example = "NewPasswordOfJohn")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, message = "Password size should be at least 8 characters")
    private String password;
}
