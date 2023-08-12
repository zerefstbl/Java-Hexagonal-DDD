package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.application.Category.create.CreateCategoryCommand;
import com.fullcycle.admin.catalogo.application.Category.create.DefaultCreateCategoryUseCase;
import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;


@ExtendWith(MockitoExtension.class)
public class CreateCategoryUserCaseTest {


    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    public void givenAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDesciption = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDesciption, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                            Objects.equals(expectedName, aCategory.getName())
                                    && Objects.equals(expectedDesciption, aCategory.getDescription())
                                    && Objects.equals(expectedIsActive, aCategory.getActive())
                                    && Objects.nonNull(aCategory.getId())
                                    && Objects.nonNull(aCategory.getCreatedAt())
                                    && Objects.nonNull(aCategory.getUpdatedAt())
                                    && Objects.isNull(aCategory.getDeletedAt())
                ));

    }


    @Test
    public void givenAInvalidName_whenCallsCreateCaegory_thenShouldReturnsDomainException() {
        final String expectedName = null;
        final var expectedDesciption = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErroCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDesciption, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErroCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(categoryGateway, Mockito.times(0)).create(Mockito.any());
    }

    @Test
    public void givenAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {
        final var expectedName = "Filmes";
        final var expectedDesciption = "A categoria mais assistida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDesciption, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName())
                                && Objects.equals(expectedDesciption, aCategory.getDescription())
                                && Objects.equals(expectedIsActive, aCategory.getActive())
                                && Objects.nonNull(aCategory.getId())
                                && Objects.nonNull(aCategory.getCreatedAt())
                                && Objects.nonNull(aCategory.getUpdatedAt())
                                && Objects.nonNull(aCategory.getDeletedAt())
                ));

    }

    @Test
    public void givenAValidCommand_whenGatewayRandomException_shouldReturnAException() {
        final var expectedName = "Filmes";
        final var expectedDesciption = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDesciption, expectedIsActive);

        Mockito.when(categoryGateway.create(Mockito.any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));


        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Mockito.verify(categoryGateway, Mockito.times(1))
                .create(Mockito.argThat(aCategory ->
                        Objects.equals(expectedName, aCategory.getName())
                                && Objects.equals(expectedDesciption, aCategory.getDescription())
                                && Objects.equals(expectedIsActive, aCategory.getActive())
                                && Objects.nonNull(aCategory.getId())
                                && Objects.nonNull(aCategory.getCreatedAt())
                                && Objects.nonNull(aCategory.getUpdatedAt())
                                && Objects.isNull(aCategory.getDeletedAt())
                ));
    }

}
