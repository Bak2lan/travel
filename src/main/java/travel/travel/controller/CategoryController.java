package travel.travel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travel.travel.model.dto.request.CategoryRequest;
import travel.travel.model.dto.response.CategoryPagination;
import travel.travel.model.dto.response.CategoryResponse;
import travel.travel.model.dto.response.SimpleResponse;
import travel.travel.service.CategoryService;

@Tag(name = "REST APIs for Category in Tourism",
        description = "CRUD REST APIs to CREATE, READ, UPDATE, DELETE a Category in Tourism")
@RestController
@RequestMapping("/api/categories")

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(
            summary = "Save category",
            description = "REST API to save category by administrator"
    )
    @PostMapping("/save")
    public ResponseEntity<SimpleResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        SimpleResponse simpleResponse = categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }
    @Operation(
            summary = "Get category by ID",
            description = "REST API to get category"
    )
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse byId = categoryService.getById(id);
        return ResponseEntity.ok(byId);
    }
    @Operation(
            summary = "Get all categories",
            description = "REST API to get all categories"
    )
    @GetMapping("/getAll")
    public ResponseEntity<CategoryPagination> getAllCategory(@RequestParam(defaultValue = "1") int currentPage,
                                                             @RequestParam(defaultValue = "5") int pageSize) {
        CategoryPagination allCategory = categoryService.getAllCategory(currentPage, pageSize);
        return ResponseEntity.ok(allCategory);
    }
    @Operation(
            summary = "Update category",
            description = "REST API to update category by administrator"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<SimpleResponse> updateCategory(@PathVariable Long id,
                                                         @RequestBody CategoryRequest categoryRequest) {
        SimpleResponse simpleResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }
    @Operation(
            summary = "Delete category",
            description = "REST API to delete category by administrator"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleResponse> deleteCategory(@PathVariable Long id){
        SimpleResponse simpleResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(simpleResponse.getStatus()).body(simpleResponse);
    }
}
