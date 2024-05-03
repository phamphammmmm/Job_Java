package org.datdev.job.services.Job;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.job.JobDTO;
import org.datdev.job.entities.Job;
import org.datdev.job.entities.Role;
import org.datdev.job.mappers.IJobMapper;
import org.datdev.job.repositories.Job.IJobRepository;
import org.datdev.job.repositories.Role.IRoleRepository;
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
    public CompletableFuture<Optional<JobDTO>> createJobAsync(JobDTO jobDTO) {
        return CompletableFuture.supplyAsync(() -> {
            Job job = IJobMapper.INSTANCE.jobDTOToJob(jobDTO);
            return Optional.of(IJobMapper.INSTANCE.jobToJobDTO(jobRepository.save(job)));
        });
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
