package co.pupil.squarecodingtest.service;

import co.pupil.squarecodingtest.entity.Shape;
import co.pupil.squarecodingtest.exceptions.CollisionException;
import co.pupil.squarecodingtest.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShapeService {
    private final ShapeRepository repository;
    private final GeometryDescriptorService geometryDescriptorService;

    public Shape save(Shape shape) {
        if (geometryDescriptorService.checkCollision(shape.getGeometryDescriptor())) {
            throw new CollisionException();
        }
        return repository.save(shape);
    }

    public Page<Shape> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
