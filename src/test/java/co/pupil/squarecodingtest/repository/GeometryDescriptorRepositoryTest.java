package co.pupil.squarecodingtest.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import co.pupil.squarecodingtest.entity.GeometryDescriptor;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeometryDescriptorRepositoryTest {

    @Autowired
    private GeometryDescriptorRepository repository;

    @BeforeEach
    void setUp() {
        repository.saveAll(List.of(
                createGeometryDescriptor(1, 1, 3),
                createGeometryDescriptor(4, 1, 2)
        ));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("provideCheckCollisionArguments")
    void checkCollision(int x, int y, int size, boolean expected) {
        assertThat(repository.checkCollision(x, y, size), is(expected));
    }

    private static Stream<Arguments> provideCheckCollisionArguments() {
        return Stream.of(
                Arguments.of(1, 1, 3, true), //exactSame
                Arguments.of(2, 2, 1, true), //inner
                Arguments.of(0, 0, 2, true), //partial left
                Arguments.of(5, 2, 2, true), //partial right
                Arguments.of(0, 3, 10, true), //partial over
                Arguments.of(0, 0, 10, true), //over
                Arguments.of(10,10,1,false)  //no collision
        );
    }


    private GeometryDescriptor createGeometryDescriptor(int x, int y, int size) {
        return GeometryDescriptor.builder().x(x).y(y).size(size).build();
    }

}
