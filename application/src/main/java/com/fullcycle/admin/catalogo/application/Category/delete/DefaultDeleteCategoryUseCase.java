package com.fullcycle.admin.catalogo.application.Category.delete;

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryId;

import java.util.Objects;

public class DefaultDeleteCategoryUseCase extends DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;


    public DefaultDeleteCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public void execute(final String anId) {
        this.categoryGateway.deleteById(CategoryId.from(anId));
    }

}
