package org.datdev.job.services.Category;

import org.datdev.job.entities.Category;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface ICategoryService {
    CompletableFuture<List<Optional<Category>>> getAllCategoriesAsync();

    CompletableFuture<Optional<Category>> getCategoryByIdAsync(int id);

    CompletableFuture<Optional<Category>> createCategoryAsync(Category category);

    CompletableFuture<Optional<Category>> updateCategoryAsync(int id, Category category);

    CompletableFuture<Void> deleteCategoryAsync(int id);
}
