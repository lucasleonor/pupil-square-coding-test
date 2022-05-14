package co.pupil.squarecodingtest.repository;

import co.pupil.squarecodingtest.entity.GeometryDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeometryDescriptorRepository extends JpaRepository<GeometryDescriptor, Long> {

    @Query("SELECT case when count(g.id) > 0 then true else false end FROM GeometryDescriptor g WHERE g.x < :x + :size and g.x + g.size > :x and g.y < :y + :size and g.y + g.size > :y")
    boolean checkCollision(@Param("x") int x, @Param("y") int y, @Param("size") int size);
}
