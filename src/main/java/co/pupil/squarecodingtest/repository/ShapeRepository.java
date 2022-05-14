package co.pupil.squarecodingtest.repository;

import co.pupil.squarecodingtest.entity.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<Shape, Long> {
}
