package org.datdev.job.services.Tag;

import org.datdev.job.DTO.Tag.TagDTO;
import org.datdev.job.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface ITagService {
    CompletableFuture<List<Optional<Tag>>> getAllTagsAsync();

    CompletableFuture<Optional<Tag>> getTagByIdAsync(int id);

    CompletableFuture<Optional<Tag>> createTagAsync(TagDTO tag);

    CompletableFuture<Optional<Tag>> updateTagAsync(int id, Tag tag);

    CompletableFuture<Void> deleteTagAsync(int id);
}
