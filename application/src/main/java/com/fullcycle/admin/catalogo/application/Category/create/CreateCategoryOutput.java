package com.fullcycle.admin.catalogo.application.Category.create;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryId;

public record CreateCategoryOutput(CategoryId id) {


    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }



}
