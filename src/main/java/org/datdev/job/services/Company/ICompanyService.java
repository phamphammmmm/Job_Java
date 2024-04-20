package org.datdev.job.services.Category;

import org.datdev.job.entities.Company;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface ICompanyService {
    CompletableFuture<List<Optional<Company>>> getAllCompaniesAsync();

    CompletableFuture<Optional<Company>> getCompanyByIdAsync(int id);

    CompletableFuture<Optional<Company>> createCompanyAsync(Company company);

    CompletableFuture<Optional<Company>> updateCompanyAsync(int id, Company company);

    CompletableFuture<Void> deleteCompanyAsync(int id);
}
