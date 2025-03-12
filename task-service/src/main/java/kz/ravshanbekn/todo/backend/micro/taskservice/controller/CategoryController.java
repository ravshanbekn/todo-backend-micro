package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import jakarta.validation.Valid;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryCreateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryFiltersDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.category.CategoryUpdateRequestDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CategoryDto> findAllByUserId(@RequestParam Long userId) {
        return categoryService.findAllByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CategoryCreateRequestDto categoryCreateRequestDto) {
        return categoryService.create(categoryCreateRequestDto);
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getById(@PathVariable("categoryId") Long categoryId) {
        return categoryService.getById(categoryId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@RequestBody @Valid CategoryUpdateRequestDto categoryUpdateRequestDto) {
        return categoryService.update(categoryUpdateRequestDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
    }


    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> search(@RequestBody CategoryFiltersDto categoryFiltersDto) {
        return categoryService.searchByFilter(categoryFiltersDto);
    }
}
