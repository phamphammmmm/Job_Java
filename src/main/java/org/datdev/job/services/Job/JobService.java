package org.datdev.job.services.Job;

import lombok.RequiredArgsConstructor;
import org.datdev.job.entities.Job;
import org.datdev.job.repositories.Job.IJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService implements IJobService {
    private final IJobRepository jobRepository;
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<Job>>> getAllJobsAsync() {
        return CompletableFuture.supplyAsync(() -> jobRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<Job>> getJobByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> jobRepository.findById(id));
    }


    @Override
    @Transactional
    public CompletableFuture<Optional<Job>> createJobAsync(Job Job){
        return CompletableFuture.supplyAsync(() -> Optional.of(jobRepository.save(Job)));
    }


    @Override
    @Transactional
    public CompletableFuture<Optional<Job>> updateJobAsync(int id, Job job) {
        return jobRepository.findById(id)
                .map(existingRole -> {
                    job.setId(existingRole.getId());
                    return jobRepository.save(job);
                })
                .map(updatedRole -> CompletableFuture.completedFuture(Optional.of(updatedRole)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteJobAsync(int id) {
        return CompletableFuture.runAsync(() -> jobRepository.deleteById(id));
    }
}
