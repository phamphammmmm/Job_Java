package org.datdev.job.services.Category;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Category;
import org.datdev.job.repositories.Category.ICategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<Category>>> getAllCategoriesAsync() {
        return CompletableFuture.supplyAsync(() -> categoryRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<Category>> getCategoryByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> categoryRepository.findById(id));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Category>> createCategoryAsync(Category category) {
        return CompletableFuture.supplyAsync(() -> Optional.of(categoryRepository.save(category)));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Category>> updateCategoryAsync(int id, Category category) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    category.setId(existingCategory.getId());
                    return categoryRepository.save(category);
                })
                .map(updatedCategory -> CompletableFuture.completedFuture(Optional.of(updatedCategory)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteCategoryAsync(int id) {
        return CompletableFuture.runAsync(() -> categoryRepository.deleteById(id));
    }
}
