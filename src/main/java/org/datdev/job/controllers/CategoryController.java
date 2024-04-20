package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Category;
import org.datdev.job.services.Category.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Category>>>> getAllCategories() {
        return categoryService.getAllCategoriesAsync()
                .thenApply(categories -> new ResponseEntity<>(categories, HttpStatus.OK));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Category>> createCategory(@RequestBody Category category) {
        return categoryService.createCategoryAsync(category)
                .thenApply(createdCategory -> ResponseEntity.ok(createdCategory.orElse(null)))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Category>> updateRole(@PathVariable int id, @RequestBody Category category) {
        return categoryService.updateCategoryAsync(id, category)
                .thenApply(updatedCategory -> updatedCategory.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteRole(@PathVariable int id) {
        return categoryService.deleteCategoryAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
