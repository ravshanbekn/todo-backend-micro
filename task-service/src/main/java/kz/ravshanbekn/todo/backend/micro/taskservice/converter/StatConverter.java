package kz.ravshanbekn.todo.backend.micro.taskservice.converter;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.StatDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Stat;
import org.springframework.stereotype.Component;


@Component
public class StatConverter {

    public StatDto toDto(Stat stat) {
        StatDto statDto = new StatDto();
        statDto.setId(stat.getId());
        statDto.setCompletedTotal(stat.getCompletedTotal());
        statDto.setUncompletedTotal(stat.getUncompletedTotal());
        statDto.setUserId(stat.getUserId());
        return statDto;
    }
}
