package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import kz.ravshanbekn.todo.backend.micro.taskservice.converter.CategoryConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.exception.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Category;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.CategoryRepository;
import kz.ravshanbekn.todo.backend.micro.taskservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryConverter categoryConverter;
    private final CategoryRepository categoryRepository;
    private final UserValidator userValidator;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAllByUserId(Long userId) {
        userValidator.validateUserExistence(userId);
        List<Category> allUserCategories = categoryRepository.findAllByUserIdOrderByTitleAsc(userId);
        return allUserCategories.stream().map(categoryConverter::toDto).toList();
    }

    @Transactional
    public CategoryDto create(Long userId, CategoryCreateRequestDto categoryCreateRequestDto) {
        userValidator.validateUserExistence(userId);
        Category category = categoryConverter.toEntity(categoryCreateRequestDto);
        category.setUserId(userId);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.toDto(savedCategory);
    }

    @Transactional
    public CategoryDto update(Long categoryId, CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = getCategoryById(categoryId);
        categoryConverter.updateCategory(categoryUpdateRequestDto, category);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.toDto(savedCategory);
    }

    @Transactional(readOnly = true)
    public CategoryDto getById(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return categoryConverter.toDto(category);
    }

    @Transactional
    public void delete(Long categoryId) {
        Category category = getCategoryById(categoryId);
        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> searchByFilter(Long userId, CategoryFiltersDto categoryFilterDto) {
        List<Category> categories = categoryRepository.findCategories(userId, categoryFilterDto.getTitle());
        return categories.stream().map(categoryConverter::toDto).toList();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found by ID: " + categoryId));
    }
}
