package kz.ravshanbekn.todo.backend.micro.taskservice.service;

import jakarta.persistence.EntityNotFoundException;
import kz.ravshanbekn.todo.backend.micro.taskservice.converter.CategoryConverter;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Category;
import kz.ravshanbekn.todo.backend.micro.taskservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryConverter categoryConverter;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAllByUserId(Long userId) {
        List<Category> allUserCategories = categoryRepository.findAllByUserIdOrderByTitleAsc(userId);
        return allUserCategories.stream().map(categoryConverter::toDto).toList();
    }

    @Transactional
    public CategoryDto create(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = categoryConverter.toEntity(categoryCreateRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.toDto(savedCategory);
    }

    @Transactional
    public CategoryDto update(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = getCategoryById(categoryUpdateRequestDto.getId());
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
    public List<CategoryDto> searchByFilter(CategoryFiltersDto categoryFilterDto) {
        List<Category> categories = categoryRepository.findCategories(categoryFilterDto.getUserId(), categoryFilterDto.getTitle());
        return categories.stream().map(categoryConverter::toDto).toList();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found by ID: " + categoryId));
    }
}
