package co.pupil.squarecodingtest.controller;

import static co.pupil.squarecodingtest.ShapeFactory.createShape;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.pupil.squarecodingtest.entity.Shape;
import co.pupil.squarecodingtest.repository.ShapeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShapeRepository shapeRepository;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        shapeRepository.saveAll(List.of(
                createShape("a", 1, 1, 3),
                createShape("b", 4, 1, 2),
                createShape("c", 1, 4, 1)
        ));
    }

    @AfterEach
    void tearDown() {
        shapeRepository.deleteAll();
    }

    @Test
    void addShapeShouldSaveShape() throws Exception {
        Shape shape = createShape("d", 4, 4, 3);
        mockMvc
                .perform(post("/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shape)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));

        assertTrue(shapeRepository.findOne(Example.of(shape)).isPresent());
    }

    @Test
    void addShapeCollisionShouldFail() throws Exception {
        Shape shape = createShape("collision", 2, 2, 1);
        mockMvc
                .perform(post("/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shape)))
                .andExpect(status().isBadRequest());

        assertTrue(shapeRepository.findOne(Example.of(shape)).isEmpty());
    }

    @Test
    void listAllShapes() throws Exception {
        mockMvc.perform(get("/shapes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

}
