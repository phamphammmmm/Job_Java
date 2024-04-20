package org.datdev.job.services.Company;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Company;
import org.datdev.job.repositories.Company.ICompanyRepository;
import org.datdev.job.services.Category.ICompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {
    private final ICompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<Company>>> getAllCompaniesAsync() {
        return CompletableFuture.supplyAsync(() -> companyRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<Company>> getCompanyByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> companyRepository.findById(id));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Company>> createCompanyAsync(Company category) {
        return CompletableFuture.supplyAsync(() -> Optional.of(companyRepository.save(category)));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Company>> updateCompanyAsync(int id, Company category) {
        return companyRepository.findById(id)
                .map(existingCategory -> {
                    category.setId(existingCategory.getId());
                    return companyRepository.save(category);
                })
                .map(updatedCategory -> CompletableFuture.completedFuture(Optional.of(updatedCategory)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteCompanyAsync(int id) {
        return CompletableFuture.runAsync(() -> companyRepository.deleteById(id));
    }
}
