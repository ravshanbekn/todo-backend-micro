package kz.ravshanbekn.todo.backend.micro.taskservice.converter;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category toEntity(CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = new Category();
        category.setTitle(categoryCreateRequestDto.getTitle());
        category.setCompletedCount(0L);
        category.setUncompletedCount(0L);
        category.setUserId(categoryCreateRequestDto.getUserId());
        return category;
    }

    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setCompletedCount(category.getCompletedCount());
        categoryDto.setUncompletedCount(category.getUncompletedCount());
        return categoryDto;
    }

    public void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto, Category category) {
        category.setTitle(categoryUpdateRequestDto.getTitle());
    }
}
