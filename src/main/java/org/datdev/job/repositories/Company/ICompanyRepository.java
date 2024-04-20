package org.datdev.job.repositories.Company;

import org.datdev.job.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company, Integer> {
}
