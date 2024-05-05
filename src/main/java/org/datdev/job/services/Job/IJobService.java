package org.datdev.job.services.Job;

import org.datdev.job.DTO.job.JobDTO;
import org.datdev.job.entities.Job;
import org.datdev.job.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IJobService{
    CompletableFuture<List<Optional<Job>>> getAllJobsAsync();

    CompletableFuture<Optional<Job>> getJobByIdAsync(int id);

    CompletableFuture<Optional<Job>> createJobAsync(Job Job);

    CompletableFuture<Optional<Job>> updateJobAsync(int id, Job job);

    CompletableFuture<Void> deleteJobAsync(int id);
}
