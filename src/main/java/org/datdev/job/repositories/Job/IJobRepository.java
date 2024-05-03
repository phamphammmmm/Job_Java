package org.datdev.job.repositories.Job;

import org.datdev.job.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends JpaRepository<Job, Integer> {
}
