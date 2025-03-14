package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.PriorityConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.priority.PriorityUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Priority;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityConverter priorityConverter;
    private final PriorityRepository priorityRepository;

    @Transactional(readOnly = true)
    public List<PriorityDto> findAll(Long userId) {
        List<Priority> priorities = priorityRepository.findByUserIdOrderByIdAsc(userId);
        return priorities.stream().map(priorityConverter::toDto).toList();
    }

    @Transactional
    public PriorityDto create(PriorityCreateRequestDto priorityCreateRequestDto) {
        Priority priority = priorityConverter.toEntity(priorityCreateRequestDto);
        Priority savedPriority = priorityRepository.save(priority);
        return priorityConverter.toDto(savedPriority);
    }

    @Transactional
    public PriorityDto update(PriorityUpdateRequestDto priorityUpdateRequestDto) {
        Priority priority = getPriorityById(priorityUpdateRequestDto.getId());
        priorityConverter.update(priorityUpdateRequestDto, priority);
        Priority updatedPriority = priorityRepository.save(priority);
        return priorityConverter.toDto(updatedPriority);
    }

    @Transactional(readOnly = true)
    public PriorityDto findById(Long priorityId) {
        Priority priority = getPriorityById(priorityId);
        return priorityConverter.toDto(priority);
    }

    @Transactional
    public void deleteById(Long priorityId) {
        Priority priority = getPriorityById(priorityId);
        priorityRepository.delete(priority);
    }

    @Transactional(readOnly = true)
    public List<PriorityDto> searchByFilter(PriorityFiltersDto priorityFiltersDto) {
        List<Priority> priorities = priorityRepository.findPriorities(priorityFiltersDto.getUserId(), priorityFiltersDto.getTitle());
        return priorities.stream().map(priorityConverter::toDto).toList();
    }

    public Priority getPriorityById(Long priorityId) {
        return priorityRepository.findById(priorityId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find priority with id: " + priorityId));
    }
}
