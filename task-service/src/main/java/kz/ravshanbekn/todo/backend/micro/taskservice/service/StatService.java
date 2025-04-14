package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.StatConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.StatDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Stat;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.StatRepository;
import kz.ravshanbekn.todo.backend.micro.taskservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatService {

    private final StatConverter statConverter;
    private final StatRepository statRepository;
    private final UserValidator userValidator;

    public StatDto findByUserId(Long userId) {
        Stat stat = statRepository.findByUserId(userId)
                .orElseGet(() -> {
                    userValidator.validateUserExistence(userId);
                    Stat newStat = Stat.builder()
                            .completedTotal(0L)
                            .uncompletedTotal(0L)
                            .userId(userId)
                            .build();
                    return statRepository.save(newStat);
                });
        return statConverter.toDto(stat);
    }
}
