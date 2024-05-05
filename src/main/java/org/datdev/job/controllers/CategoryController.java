package org.datdev.job.controllers;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.category.CategoryDTO;
import org.datdev.job.entities.Category;
import org.datdev.job.entities.Company;
import org.datdev.job.services.Category.ICategoryService;
import org.datdev.job.services.Excel.IExcelService;
import org.datdev.job.services.QRCode.IQrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private final IExcelService<Category> categoryExcelService;
    private final IQrCodeService qrCodeService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Category>>>> getAllCategories() {
        return categoryService.getAllCategoriesAsync()
                .thenApply(categories -> new ResponseEntity<>(categories, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Optional<Category>>> getCategoriesById(@PathVariable int id) {
        return categoryService.getCategoryByIdAsync(id)
                .thenApply(categories -> new ResponseEntity<>(categories, HttpStatus.OK));
    }
    @PostMapping
    public CompletableFuture<ResponseEntity<Category>> createCategory(@RequestBody CategoryDTO category) {
        return categoryService.createCategoryAsync(category)
                .thenApply(createdCategory -> {
                    return ResponseEntity.ok(createdCategory.orElse(null));
                })
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
    @GetMapping("/export")
    public void exportCategoriesToExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=categories.xlsx");
            OutputStream outputStream = response.getOutputStream();

            // Chuyển đổi từ Optional<Category> sang Category
            List<Category> categories = categoryService.getAllCategoriesAsync()
                    .join().stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            // Gọi phương thức exportExcel
            categoryExcelService.exportExcel(categories, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        }
    }

    @PostMapping("/import")
    public CompletableFuture<ResponseEntity<String>> importCategoriesFromExcel(@RequestBody byte[] excelData) {
        try {
            categoryExcelService.importFromExcel(new ByteArrayInputStream(excelData));
            return CompletableFuture.completedFuture(ResponseEntity.ok("Import successful"));
        } catch (IOException e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Import failed"));
        }
    }

}
