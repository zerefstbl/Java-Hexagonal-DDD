package com.fullcycle.admin.catalogo.application.Category.retrieve.get;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryId;

import java.time.Instant;

public record CategoryOutput(CategoryId id, String name, String description, boolean isActive, Instant createdAt, Instant updatedAt, Instant deletedAt) {

    public static CategoryOutput from(final Category aCategory) {
        return new CategoryOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getUpdatedAt(),
                aCategory.getDeletedAt()
        );
    }

}
