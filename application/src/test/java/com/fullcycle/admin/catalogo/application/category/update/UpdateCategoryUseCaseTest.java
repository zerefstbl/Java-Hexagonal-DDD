package com.fullcycle.admin.catalogo.application.category.update;


import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @InjectMocks
    private DefaultUpdateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallsUpdateCategory_shouldReturnCategoryId() {
        final var aCategory = Category.newCategory("Film", null, true);

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedId = aCategory.getId();

        final var aCommand = UpdateCategoryCommand.with(expectedId.getValue(), expectedName, expectedDescription, expectedIsActive);

        Mockito.when(categoryGateway.findById(Mockito.eq(expectedId)))
                .thenReturn(Optional.of(aCategory));

        Mockito.when(categoryGateway.update(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutPut = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutPut);
        Assertions.assertNotNull(actualOutPut.getId());

        Mockito.verify(categoryGateway, Mockito.times(1)).findById(Mockito.eq(expectedId));
        Mockito.verify(categoryGateway, Mockito.times(1)).update(Mockito.argThat(aUpdatedCategory ->
                Objects.equals(expectedName, aUpdatedCategory.getName())
                && Objects.equals(expectedDescription, aUpdatedCategory.getDescription())
                && Objects.equals(expectedIsActive, aUpdatedCategory.getActive())
                && Objects.equals(expectedId, aUpdatedCategory.getId())
                && Objects.equals(aCategory.getCreatedAt(), aUpdatedCategory.getCreatedAt())
                && aCategory.getUpdatedAt().isBefore(aUpdatedCategory.getUpdatedAt())
                && Objects.isNull(aUpdatedCategory.getDeletedAt())
        ));
    }

}
