package co.pupil.squarecodingtest.service;

import co.pupil.squarecodingtest.entity.GeometryDescriptor;
import co.pupil.squarecodingtest.repository.GeometryDescriptorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeometryDescriptorService {
    private final GeometryDescriptorRepository repository;

    public boolean checkCollision(GeometryDescriptor geometryDescriptor) {
        return repository.checkCollision(geometryDescriptor.getX(), geometryDescriptor.getY(), geometryDescriptor.getSize());
    }
}
