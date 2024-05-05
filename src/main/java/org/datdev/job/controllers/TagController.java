package org.datdev.job.controllers;

import lombok.RequiredArgsConstructor;
import org.datdev.job.DTO.Tag.TagDTO;
import org.datdev.job.entities.Role;
import org.datdev.job.entities.Tag;
import org.datdev.job.services.Tag.ITagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final ITagService tagService;

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Optional<Tag>>>> getAllRoles() {
        return tagService.getAllTagsAsync()
                .thenApply(roles -> new ResponseEntity<>(roles, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Optional<Tag>>> getTagById(@PathVariable int id) {
        return tagService.getTagByIdAsync(id)
                .thenApply(tag -> new ResponseEntity<>(tag, HttpStatus.OK));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Tag>> createRole(@RequestBody TagDTO tag) {
        return tagService.createTagAsync(tag)
                .thenApply(createdRole -> ResponseEntity.ok(createdRole.orElse(null)))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
    }

    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Tag>> updateRole(@PathVariable int id, @RequestBody Tag tag) {
        return tagService.updateTagAsync(id, tag)
                .thenApply(updatedRole -> updatedRole.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteRole(@PathVariable int id) {
        return tagService.deleteTagAsync(id)
                .thenApply(ignored -> ResponseEntity.noContent().build());
    }
}
