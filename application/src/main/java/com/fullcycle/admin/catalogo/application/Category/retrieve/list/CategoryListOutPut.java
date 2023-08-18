package com.fullcycle.admin.catalogo.application.Category.retrieve.list;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryId;

import java.time.Instant;

public record CategoryListOutPut(
        CategoryId id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant deletedAt
) {

    public static CategoryListOutPut from(final Category aCategory) {
        return new CategoryListOutPut(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt()
        );
    }

}
