package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Retrieve all tasks by user ID",
            description = "Returns a list of tasks that belong to the specified user.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tasks successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> findAllByUserId(@RequestParam Long userId) {
        return categoryService.findAllByUserId(userId);
    }

    @Operation(
            summary = "Create a new category",
            description = "Creates a new category for the specified user.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Category successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestParam Long userId,
                              @RequestBody @Valid CategoryCreateRequestDto categoryCreateRequestDto) {
        return categoryService.create(userId, categoryCreateRequestDto);
    }

    @Operation(
            summary = "Get category by ID",
            description = "Retrieves a category by its unique ID.",
            parameters = {
                    @Parameter(name = "categoryId", description = "Category ID", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid category ID"),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
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

    @Operation(
            summary = "Delete category by ID",
            description = "Deletes a category by its unique ID.",
            parameters = {
                    @Parameter(name = "categoryId", description = "Category ID to delete", required = true, example = "10")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Invalid category ID"),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
    }

    @Operation(
            summary = "Search categories by filters",
            description = "Searches categories based on user ID and additional filter criteria.",
            parameters = {
                    @Parameter(name = "userId", description = "User ID", required = true, example = "123")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categories successfully retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> search(@RequestParam Long userId,
                                    @RequestBody CategoryFiltersDto categoryFiltersDto) {
        return categoryService.searchByFilter(userId, categoryFiltersDto);
    }
}
