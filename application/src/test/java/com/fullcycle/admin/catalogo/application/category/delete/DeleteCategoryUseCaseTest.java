package com.fullcycle.admin.catalogo.application.category.delete;


import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

    @InjectMocks
    private DefaultDeleteCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(categoryGateway);
    }

    @Test
    public void givenAValidId_whenCallsDeleteCategory_shouldBeOk() {


        final var aCategory = Category.newCategory("Film", "A categoria mais assistida", true);

        final var expectedId = aCategory.getId();

        Mockito.doNothing()
                .when(categoryGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));

    }

    @Test
    public void givenAInvalidId_whenCallsDeleteCategory_shouldBeOk() {

        final var expectedId = CategoryId.from("123");

         Mockito.doNothing()
                 .when(categoryGateway).deleteById(expectedId);

         Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

         Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));

    }

    @Test
    public void givenAValidId_whenGatewayThrowsError_shouldReturnException() {


        final var aCategory = Category.newCategory("Filme", "A categoria mais assistida", true);

        final var expectedId = aCategory.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(categoryGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(categoryGateway, Mockito.times(1)).deleteById(Mockito.eq(expectedId));


    }


}
