package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.StatConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.StatDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Stat;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.StatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatService {

    private final StatConverter statConverter;
    private final StatRepository statRepository;

    public StatDto findByUserId(Long userId) {
        Stat stat = statRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Stat not found by user ID: " + userId));
        return statConverter.toDto(stat);
    }
}
