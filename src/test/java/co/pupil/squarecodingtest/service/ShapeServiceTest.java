package co.pupil.squarecodingtest.service;

import static co.pupil.squarecodingtest.ShapeFactory.createShape;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.pupil.squarecodingtest.entity.Shape;
import co.pupil.squarecodingtest.exceptions.CollisionException;
import co.pupil.squarecodingtest.repository.ShapeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
class ShapeServiceTest {

    @Mock
    private ShapeRepository shapeRepository;
    @Mock
    private GeometryDescriptorService geometryDescriptorService;
    @InjectMocks
    private ShapeService shapeService;


    @Test
    void saveWithNoCollisionShouldSaveObject() {
        Shape input = createShape("a", 1, 1, 1);
        Shape expected = createShape("b", 2, 2, 2);

        when(geometryDescriptorService.checkCollision(input.getGeometryDescriptor())).thenReturn(false);
        when(shapeRepository.save(input)).thenReturn(expected);

        Shape actual = shapeService.save(input);

        assertThat(actual, is(expected));
    }

    @Test
    void saveWithCollisionShouldFail() {
        Shape input = createShape("a", 1, 1, 1);

        when(geometryDescriptorService.checkCollision(input.getGeometryDescriptor())).thenReturn(true);

        assertThrows(CollisionException.class, () -> shapeService.save(input));

        verify(shapeRepository, never()).save(any());
    }
}
