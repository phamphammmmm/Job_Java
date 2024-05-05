package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.job.JobDTO;
import org.datdev.job.entities.Job;
import org.datdev.job.entities.Role;
import org.datdev.job.services.Job.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {
    private final IJobService jobService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Job>>>> getAllJobs() {
        return jobService.getAllJobsAsync()
                .thenApply(jobs -> new ResponseEntity<>(jobs, HttpStatus.OK));
    }
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Optional<Job>>> getJobById(@PathVariable int id) {
        return jobService.getJobByIdAsync(id)
                .thenApply(job -> new ResponseEntity<>(job, HttpStatus.OK));
    }
    @PostMapping
    public CompletableFuture<ResponseEntity<Job>> createJob(@RequestBody Job Job) {
        try {
            return jobService.createJobAsync(Job)
                    .thenApply(createdJob -> ResponseEntity.ok(createdJob.orElse(null)))
                    .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
        }
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Job>> updateJob(@PathVariable int id, @RequestBody Job job) {
        return jobService.updateJobAsync(id, job)
                .thenApply(updatedJob -> updatedJob.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteJob(@PathVariable int id) {
        return jobService.deleteJobAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
