package com.fullcycle.admin.catalogo.application.Category.retrieve.list;

import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategorySearchQuery;
import com.fullcycle.admin.catalogo.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase{


    private final CategoryGateway categoryGateway;


    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutPut> execute(final CategorySearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery)
                .map(CategoryListOutPut::from);
    }


}
