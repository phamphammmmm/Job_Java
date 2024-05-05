package org.datdev.job.services.Tag;

import lombok.RequiredArgsConstructor;

import org.datdev.job.DTO.Tag.TagDTO;
import org.datdev.job.entities.Role;
import org.datdev.job.entities.Tag;
import org.datdev.job.repositories.Tag.ITagRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {
    private final ITagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<List<Optional<Tag>>> getAllTagsAsync() {
        return CompletableFuture.supplyAsync(() -> tagRepository.findAll()
                .stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public CompletableFuture<Optional<Tag>> getTagByIdAsync(int id) {
        return CompletableFuture.supplyAsync(() -> tagRepository.findById(id));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Tag>> createTagAsync(TagDTO tag){
        Tag newTag = new Tag();
        newTag.setName(tag.Name);
        return CompletableFuture.supplyAsync(() -> Optional.of(tagRepository.save(newTag)));
    }

    @Override
    @Transactional
    public CompletableFuture<Optional<Tag>> updateTagAsync(int id, Tag tag) {
        return tagRepository.findById(id)
                .map(existingRole -> {
                    tag.setId(existingRole.getId());
                    return tagRepository.save(tag);
                })
                .map(updatedTag -> CompletableFuture.completedFuture(Optional.of(updatedTag)))
                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty()));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteTagAsync(int id) {
        return CompletableFuture.runAsync(() -> tagRepository.deleteById(id));
    }
}
