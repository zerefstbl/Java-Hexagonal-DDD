package com.fullcycle.admin.catalogo.domain.category;

import java.time.Instant;

import com.fullcycle.admin.catalogo.domain.AggregateRoot;
import com.fullcycle.admin.catalogo.domain.validation.ValidationHandler;

public class Category extends AggregateRoot<CategoryId> implements Cloneable {
    private String name;
    private String description;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    private Category(
        final CategoryId anId,
        final String anName,
        final String anDescription,
        final boolean anActive,
        final Instant anCreatedAt,
        final Instant anUpdatedAt,
        final Instant anDeletedAt
    ) {
        super(anId);
        this.name = anName;
        this.description = anDescription;
        this.active = anActive;
        this.createdAt = anCreatedAt;
        this.updatedAt = anUpdatedAt;
        this.deletedAt = anDeletedAt;
    }

    public static Category newCategory(final String aName, final String aDescription, final boolean aIsActive) {

        final var id = CategoryId.unique();

        final var now = Instant.now();

        final var aDeletedAt = aIsActive ? null : now;

        return new Category(id, aName, aDescription, aIsActive, now, now, aDeletedAt);
    }


    public static Category with(final Category aCategory) {
        return new Category(
                aCategory.getId(),
                aCategory.name,
                aCategory.description,
                aCategory.isActive(),
                aCategory.createdAt,
                aCategory.updatedAt,
                aCategory.deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }


    public Category activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Category deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;

        this.updatedAt = Instant.now();

        return this;

    }

    public Category update(final String aName, final String aDescription, final boolean isActive) {
        this.name = aName;
        this.description = aDescription;
        if (isActive) {
            activate();
        } else {
            deactivate();
        }

        this.updatedAt = Instant.now();

        return this;
    }

    public CategoryId getId() {
        return this.id;
    }

    // public void setId(String id) {
        // this.id = id;
    // }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public Category clone() {
        try {
            return (Category) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
