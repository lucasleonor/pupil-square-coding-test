package co.pupil.squarecodingtest.controller;

import co.pupil.squarecodingtest.entity.Shape;
import co.pupil.squarecodingtest.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
@RequestMapping("/shapes")
@RequiredArgsConstructor
public class ShapeController {
    private final ShapeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shape addShape(@Validated @RequestBody Shape shape) {
        return service.save(shape);
    }

    @GetMapping
    public Page<Shape> listAllShapes(Pageable pageable) {
        return service.findAll(pageable);
    }

}
