package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Company;
import org.datdev.job.entities.Role;
import org.datdev.job.services.Category.ICompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final ICompanyService companyService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Company>>>> getAllCompanies() {
        return companyService.getAllCompaniesAsync()
                .thenApply(categories -> new ResponseEntity<>(categories, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Optional<Company>>> getCompanyById(@PathVariable int id) {
        return companyService.getCompanyByIdAsync(id)
                .thenApply(company -> new ResponseEntity<>(company, HttpStatus.OK));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Company>> createCategory(@RequestBody Company company) {
        return companyService.createCompanyAsync(company)
                .thenApply(createdCategory -> ResponseEntity.ok(createdCategory.orElse(null)))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Company>> updateRole(@PathVariable int id, @RequestBody Company company) {
        return companyService.updateCompanyAsync(id, company)
                .thenApply(updatedCategory -> updatedCategory.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteRole(@PathVariable int id) {
        return companyService.deleteCompanyAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
