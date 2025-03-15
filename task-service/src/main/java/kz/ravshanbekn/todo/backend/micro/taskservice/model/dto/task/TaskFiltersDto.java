package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskFiltersDto {

    private String title;
    private Boolean completed;
    private Long priorityId;
    private Long categoryId;

    @NotBlank(message = "Sort column should not be blank")
    private String sortColumn;
    @NotBlank(message = "Sort direction should not be blank")
    private String sortDirection;

    @NotNull(message = "Page number should not be null")
    private Integer pageNumber;
    @NotNull(message = "Page size should not be null")
    private Integer pageSize;

    private Date dateFrom;
    private Date dateTo;
}
