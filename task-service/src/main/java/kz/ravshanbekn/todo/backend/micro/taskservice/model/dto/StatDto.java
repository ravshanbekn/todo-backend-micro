package kz.ravshanbekn.todo.backend.micro.taskservice.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StatDto {

    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;
    private Long userId;
}
